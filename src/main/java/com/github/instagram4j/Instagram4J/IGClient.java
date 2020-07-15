package com.github.instagram4j.Instagram4J;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.net.CookieManager;
import java.net.Proxy;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.instagram4j.Instagram4J.exceptions.IGChallengeException;
import com.github.instagram4j.Instagram4J.exceptions.IGLoginException;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.models.user.IGProfile;
import com.github.instagram4j.Instagram4J.requests.IGRequest;
import com.github.instagram4j.Instagram4J.requests.accounts.IGLoginRequest;
import com.github.instagram4j.Instagram4J.requests.accounts.IGTwoFactorLoginRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import okhttp3.CookieJar;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

@Data
@Slf4j
public class IGClient implements Serializable {
    private static final long serialVersionUID = -893265874837l;
    private final String $username;
    private final String $password;
    @JsonIgnore
    private transient OkHttpClient httpClient;
    private String deviceId;
    private String guid;
    private String phoneId;
    @Setter(AccessLevel.PRIVATE)
    private boolean loggedIn = false;
    @Setter(AccessLevel.PRIVATE)
    private IGProfile selfProfile;
    private IGDevice device = IGAndroidDevice.GOOD_DEVICES[0];

    // logging
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor((msg) -> {
        log.debug(msg);
    }).setLevel(Level.BODY);

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

    public IGLoginResponse sendLoginRequest() throws IGLoginException, IGResponseException {
        log.debug("Logging in. . .");
        IGLoginRequest login = new IGLoginRequest($username, $password);
        IGLoginResponse res = this.sendRequest(login);
        log.debug("Response is : " + res.getStatus());
        this.setLoggedInState(res);

        return res;
    }

    public IGLoginResponse sendLoginRequest(String code, String identifier)
            throws IGLoginException, IGResponseException {
        log.debug("Logging in. . .");
        IGTwoFactorLoginRequest login = new IGTwoFactorLoginRequest($username, $password, code, identifier);
        IGLoginResponse res = this.sendRequest(login);
        log.debug("Response is : " + res.getStatus());
        this.setLoggedInState(res);

        return res;
    }

    public <T extends IGResponse> T sendRequest(@NonNull IGRequest<T> req) throws IGResponseException {
        return sendRequest(req, req.getResponseType());
    }

    public <T> T sendRequest(@NonNull IGRequest<?> req, Class<T> view) throws IGResponseException {
        Response res;
        try {
            res = httpClient.newCall(req.formRequest(this)).execute();
        } catch (IOException ex) {
            throw new IGResponseException(null, "exception occured during request", ex);
        }

        try (ResponseBody body = res.body()) {
            T t = req.parseResponse(body.string(), view);
            if (t instanceof IGResponse) ((IGResponse) t).setStatusCode(res.code());
            return t;
        } catch (JsonProcessingException exception) {
            throw new IGResponseException(res, "Json processing failed", exception);
        } catch (IOException exception) {
            throw new IGResponseException(res, "malformed body received", exception);
        }
    }

    public void setLoggedInState(IGLoginResponse state) throws IGLoginException {
        if (!state.getStatus().equals("ok"))
            throw new IGLoginException(this, state);
        this.loggedIn = true;
        this.selfProfile = state.getLogged_in_user();
    }

    public String getCsrfToken() {
        return IGUtils.getCookieValue(this.getHttpClient().cookieJar(), "csrftoken").orElse("missing");
    }

    public IGPayload setIGPayloadDefaults(IGPayload load) {
        load.set_csrftoken(this.getCsrfToken());
        load.setDevice_id(this.deviceId);
        load.setGuid(this.guid);
        load.setPhone_id(this.phoneId);

        return load;
    }

    public void setCookieJar(CookieJar jar) {
        this.httpClient = this.httpClient.newBuilder().cookieJar(jar).build();
    }

    public void setProxy(Proxy proxy) {
        this.httpClient = this.httpClient.newBuilder().proxy(proxy).build();
    }

    public static IGClient.Builder builder() {
        return new IGClient.Builder();
    }

    public static IGClient from(InputStream from) throws ClassNotFoundException, IOException {
        return from(from, null, null);
    }

    public static IGClient from(InputStream from, CookieJar jar) throws ClassNotFoundException, IOException {
        return from(from, jar, null);
    }

    public static IGClient from(InputStream from, Proxy proxy) throws ClassNotFoundException, IOException {
        return from(from, null, proxy);
    }

    public static IGClient from(InputStream from, CookieJar jar, Proxy proxy)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(from)) {
            IGClient client = (IGClient) in.readObject();
            if (jar != null)
                client.setCookieJar(jar);
            if (proxy != null)
                client.setProxy(proxy);

            return client;
        }
    }

    public static IGClient from(InputStream from, CookieJar jar, Proxy proxy, @NonNull OkHttpClient httpClient)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(from)) {
            IGClient client = (IGClient) in.readObject();
            client.httpClient = httpClient;
            if (jar != null)
                client.setCookieJar(jar);
            if (proxy != null)
                client.setProxy(proxy);

            return client;
        }
    }

    private Object readResolve() throws ObjectStreamException {
        this.httpClient = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(new CookieManager())).addInterceptor(loggingInterceptor).build();

        return this;
    }

    @Accessors(fluent = true)
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Builder {
        private String withUsername;
        private String withPassword;
        private CookieJar withCookieJar;
        private OkHttpClient withClient = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(new CookieManager())).addInterceptor(loggingInterceptor).build();
        private Proxy withProxy;
        private LoginHandler onChallenge;
        private LoginHandler onTwoFactor;
        private Consumer<IGLoginResponse> onSuccessfulLogin = (login) -> {};

        public IGClient build() {
            IGClient client = new IGClient(withUsername, withPassword);
            if (withClient != null)
                client.setHttpClient(withClient);
            if (withProxy != null)
                client.setProxy(withProxy);
            if (withCookieJar != null)
                client.setCookieJar(withCookieJar);

            return client;
        }

        public IGClient login() throws IGLoginException, IGChallengeException {
            IGClient client = build();

            try {
                onSuccessfulLogin.accept(client.sendLoginRequest());
            } catch (IGResponseException exception) {
                throw new IGLoginException(exception);
            } catch (IGLoginException ex) {
                IGLoginResponse response = ex.getLoginResponse();

                if (ex.getLoginResponse().getTwo_factor_info() != null && onTwoFactor != null) {
                    response = onTwoFactor.accept(client, response);
                } 
                if (ex.getLoginResponse().getChallenge() != null && onChallenge != null) {
                    response = onChallenge.accept(client, response);
                }

                client.setLoggedInState(response); // will throw if response is not ok
                onSuccessfulLogin.accept(response);
            }

            return client;
        }

        @FunctionalInterface
        public static interface LoginHandler {
            public IGLoginResponse accept(IGClient client, IGLoginResponse t)
                    throws IGChallengeException;
        }
    }
}