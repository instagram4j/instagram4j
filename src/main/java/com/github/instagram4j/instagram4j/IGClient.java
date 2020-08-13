package com.github.instagram4j.instagram4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.github.instagram4j.instagram4j.actions.IGClientActions;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException.IGFailedResponse;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsLoginRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsTwoFactorLoginRequest;
import com.github.instagram4j.instagram4j.requests.qe.QeSyncRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.accounts.LoginResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import kotlin.Pair;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Data
@Slf4j
public class IGClient implements Serializable {
    private static final long serialVersionUID = -893265874836l;
    private final String $username;
    private final String $password;
    private transient String encryptionId, encryptionKey, authorization;
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

    public void sendSyncRequest() {
        new QeSyncRequest(true).execute(this).join();
        log.info("Encryption key id : {}", this.encryptionId);
        log.info("Encryption key (truncated) : {}", IGUtils.truncate(this.encryptionKey));
    }

    public LoginResponse sendLoginRequest() throws IGLoginException {
        if (this.encryptionId == null || this.encryptionKey == null) {
            log.info("Sending sync request. . .");
            this.sendSyncRequest();
        }
        String encryptedPassword = IGUtils.encryptPassword($password, this.encryptionId, this.encryptionKey);
        log.info("Logging in. . .");
        LoginResponse res = new AccountsLoginRequest($username, encryptedPassword).execute(this).join();
        this.setLoggedInState(res);

        return res;
    }

    public LoginResponse sendLoginRequest(String code, String identifier)
            throws IGLoginException {
        if (this.encryptionId == null || this.encryptionKey == null) {
            log.info("Sending sync request. . .");
            this.sendSyncRequest();
        }
        String encryptedPassword = IGUtils.encryptPassword($password, this.encryptionId, this.encryptionKey);
        log.info("Logging in. . .");
        LoginResponse res = new AccountsTwoFactorLoginRequest($username, encryptedPassword, code, identifier)
                .execute(this).join();
        this.setLoggedInState(res);

        return res;
    }

    public <T extends IGResponse> CompletableFuture<T> sendRequest(@NonNull IGRequest<T> req) {
        CompletableFuture<Pair<Response, String>> responseFuture = new CompletableFuture<>();
        log.info("Sending request : {}", req.formUrl(this).toString());
        this.httpClient.newCall(req.formRequest(this)).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException exception) {
                responseFuture.completeExceptionally(exception);
            }

            @Override
            public void onResponse(Call call, Response res) throws IOException {
                log.info("Response for {} : {}", call.request().url().toString(), res.code());
                try (ResponseBody body = res.body()) {
                    responseFuture.complete(new Pair<>(res, body.string()));
                }
            }

        });

        return responseFuture
                .thenApply(res -> {
                    setFromResponseHeaders(res.getFirst());
                    log.info("Response for {} with body (truncated) : {}", res.getFirst().request().url(),
                            IGUtils.truncate(res.getSecond()));

                    return req.parseResponse(res);
                })
                .exceptionally(tr -> {
                    T failedResponse = IGFailedResponse.of(tr.getCause(), req.getResponseType());
                    log.error("{} failed : {}", req.formUrl(this).toString(), failedResponse.getMessage());

                    return failedResponse;
                });
    }

    private void setLoggedInState(LoginResponse state) {
        if (!state.getStatus().equals("ok")) return;
        this.loggedIn = true;
        this.selfProfile = state.getLogged_in_user();
        log.info("Logged into {} ({})", selfProfile.getUsername(), selfProfile.getPk());
    }

    public String getCsrfToken() {
        return IGUtils.getCookieValue(this.getHttpClient().cookieJar(), "csrftoken").orElse("missing");
    }

    public void setFromResponseHeaders(Response res) {
        Optional.ofNullable(res.header("ig-set-password-encryption-key-id")).ifPresent(s -> this.encryptionId = s);
        Optional.ofNullable(res.header("ig-set-password-encryption-pub-key")).ifPresent(s -> this.encryptionKey = s);
        Optional.ofNullable(res.header("ig-set-authorization")).ifPresent(s -> this.authorization = s);
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
        
        public IGClient simulatedLogin(Consumer<List<CompletableFuture<?>>> postLoginResponses) throws IGLoginException {
            IGClient client = build();
            client.actions.simulate().preLoginFlow().forEach(CompletableFuture::join);
            onLogin.accept(performLogin(client));
            postLoginResponses.accept(client.actions.simulate().postLoginFlow());
            
            return client;
        }
        
        public IGClient simulatedLogin() throws IGLoginException {
            return simulatedLogin((res) -> {});
        }

        public IGClient login() throws IGLoginException {
            IGClient client = build();

            onLogin.accept(performLogin(client));

            return client;
        }
        
        private LoginResponse performLogin(IGClient client) throws IGLoginException {
            LoginResponse response = client.sendLoginRequest();
            if (response.getTwo_factor_info() != null && onTwoFactor != null) {
                response = onTwoFactor.accept(client, response);
            }
            if (response.getChallenge() != null && onChallenge != null) {
                response = onChallenge.accept(client, response);
                client.setLoggedInState(response);
            }
            
            if(!client.isLoggedIn()) {
                throw new IGLoginException(client, response);
            }
            
            return response;
        }

        @FunctionalInterface
        public static interface LoginHandler {
            public LoginResponse accept(IGClient client, LoginResponse t);
        }
    }
}