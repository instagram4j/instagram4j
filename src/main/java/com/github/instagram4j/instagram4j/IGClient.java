package com.github.instagram4j.instagram4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.function.Consumer;

import com.github.instagram4j.instagram4j.actions.IGClientActions;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsLoginRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsTwoFactorLoginRequest;
import com.github.instagram4j.instagram4j.requests.qe.QeSyncRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.accounts.LoginResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Data
@Slf4j
public class IGClient implements Serializable {
    private static final long serialVersionUID = -893265874836l;
    private final String $username;
    private final String $password;
    private transient String encryptionId, encryptionKey;
    private transient OkHttpClient httpClient = IGUtils.formDefaultHttpClient();
    private transient String sessionId = IGUtils.randomUuid();
    private transient IGClientActions actions = new IGClientActions(this);
    private String deviceId;
    private String guid;
    private String phoneId;
    @Setter(AccessLevel.PRIVATE)
    private boolean loggedIn = false;
    @Setter(AccessLevel.PRIVATE)
    private Profile selfProfile;
    private IGDevice device = IGAndroidDevice.GOOD_DEVICES[0];
    
    public IGClient(String username, String password) {
        this.$username = username;
        this.$password = password;
        this.guid = IGUtils.randomUuid();
        this.phoneId = IGUtils.randomUuid();
        this.deviceId = IGUtils.generateDeviceId(username, password);
    }

    public IGClient(String username, String password, OkHttpClient client) {
        this(username, password);
        this.httpClient = client;
    }
    
    public IGClientActions actions() {
        return this.actions;
    }
    
    public void sendSyncRequest() throws IOException {
        Response response = httpClient.newCall(new QeSyncRequest().formRequest(this)).execute();
        log.info("Response for {} : {}", QeSyncRequest.class.getName(), response.code());
        this.encryptionId = response.header("ig-set-password-encryption-key-id");
        this.encryptionKey = response.header("ig-set-password-encryption-pub-key");
        log.info("Encryption key id : {}", this.encryptionId);
        log.info("Encryption key (truncated) : {}", this.encryptionKey.substring(20));
    }

    public LoginResponse sendLoginRequest() throws IGLoginException, IOException {
        if (this.encryptionId == null || this.encryptionKey == null) {
            log.info("Sending sync request. . .");
            this.sendSyncRequest();
        }
        String encryptedPassword = IGUtils.encryptPassword($password, this.encryptionId, this.encryptionKey);
        log.info("Logging in. . .");
        AccountsLoginRequest login = new AccountsLoginRequest($username, encryptedPassword);
        LoginResponse res = this.sendRequest(login);
        this.setLoggedInState(res);

        return res;
    }

    public LoginResponse sendLoginRequest(String code, String identifier)
            throws IGLoginException, IOException {
        if (this.encryptionId == null || this.encryptionKey == null) {
            log.info("Sending sync request. . .");
            this.sendSyncRequest();
        }
        String encryptedPassword = IGUtils.encryptPassword($password, this.encryptionId, this.encryptionKey);
        log.info("Logging in. . .");
        AccountsTwoFactorLoginRequest login = new AccountsTwoFactorLoginRequest($username, encryptedPassword, code, identifier);
        LoginResponse res = this.sendRequest(login);
        this.setLoggedInState(res);

        return res;
    }

    public <T extends IGResponse> T sendRequest(@NonNull IGRequest<T> req) throws IOException {
        T response = sendRequest(req, req.getResponseType());
        if (response.getMessage() != null && response.getMessage().equals("login_required")) {
            this.loggedIn = false;
            throw new IGResponseException(response);
        }
        
        return response;
    }

    public <T> T sendRequest(@NonNull IGRequest<?> req, Class<T> view) throws IOException {
        log.info("Sending request : {}", req.getClass().getName());
        Response res = httpClient.newCall(req.formRequest(this)).execute();
        log.info("Response for {} : {}", req.getClass().getName(), res.code());
        try (ResponseBody body = res.body()) {
            T t = req.parseResponse(body.string(), view);
            if (t instanceof IGResponse) ((IGResponse) t).setStatusCode(res.code());
            return t;
        }
    }

    public void setLoggedInState(LoginResponse state) throws IGLoginException {
        if (!state.getStatus().equals("ok"))
            throw new IGLoginException(this, state);
        this.loggedIn = true;
        this.selfProfile = state.getLogged_in_user();
        log.info("Logged into {} ({})", selfProfile.getUsername(), selfProfile.getPk());
    }

    public String getCsrfToken() {
        return IGUtils.getCookieValue(this.getHttpClient().cookieJar(), "csrftoken").orElse("missing");
    }

    public IGPayload setIGPayloadDefaults(IGPayload load) {
        load.set_csrftoken(this.getCsrfToken());
        load.setDevice_id(this.deviceId);
        if (selfProfile != null) {
            load.set_uid(selfProfile.getPk().toString());
            load.set_uuid(this.guid);
        } else {
            load.setId(this.guid);
        }
        load.setGuid(this.guid);
        load.setPhone_id(this.phoneId);

        return load;
    }

    public static IGClient.Builder builder() {
        return new IGClient.Builder();
    }

    public static IGClient from(InputStream from) throws ClassNotFoundException, IOException {
        return from(from, IGUtils.formDefaultHttpClient());
    }

    public static IGClient from(InputStream from, @NonNull OkHttpClient httpClient)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(from)) {
            IGClient client = (IGClient) in.readObject();
            client.httpClient = httpClient;

            return client;
        }
    }

    private Object readResolve() throws ObjectStreamException {
        this.httpClient = IGUtils.formDefaultHttpClient();
        this.sessionId = IGUtils.randomUuid();
        this.actions = new IGClientActions(this);
        if (loggedIn)
            log.info("Logged into {} ({})", selfProfile.getUsername(), selfProfile.getPk());
        return this;
    }

    @Accessors(fluent = true)
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Builder {
        private String username;
        private String password;
        private OkHttpClient client = IGUtils.formDefaultHttpClient();
        private LoginHandler onChallenge;
        private LoginHandler onTwoFactor;
        private Consumer<LoginResponse> onLogin = (login) -> {};

        public IGClient build() {
            return new IGClient(username, password, client);
        }

        public IGClient login() throws IOException {
            IGClient client = build();

            try {
                onLogin.accept(client.sendLoginRequest());
            } catch (IGLoginException ex) {
                LoginResponse response = ex.getLoginResponse();

                if (ex.getLoginResponse().getTwo_factor_info() != null && onTwoFactor != null) {
                    response = onTwoFactor.accept(client, response);
                }
                
                if (ex.getLoginResponse().getChallenge() != null && onChallenge != null) {
                    response = onChallenge.accept(client, response);
                }
                
                if (!client.isLoggedIn()) throw new IGLoginException(client, response);

                onLogin.accept(response);
            }

            return client;
        }

        @FunctionalInterface
        public static interface LoginHandler {
            public LoginResponse accept(IGClient client, LoginResponse t) throws IOException;
        }
    }
}