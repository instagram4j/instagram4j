package com.github.instagram4j.Instagram4J;

import java.net.CookieManager;

import com.github.instagram4j.Instagram4J.exceptions.IGChallengeException;
import com.github.instagram4j.Instagram4J.exceptions.IGLoginException;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException.IGExceptionInfo;
import com.github.instagram4j.Instagram4J.models.IGLoggedInUser;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGRequest;
import com.github.instagram4j.Instagram4J.requests.accounts.IGLoginRequest;
import com.github.instagram4j.Instagram4J.requests.accounts.IGTwoFactorLoginRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.With;
import lombok.extern.slf4j.Slf4j;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

@Slf4j
public class IGClient {
    private final String username;
    private final String password;
    @Getter
    private OkHttpClient httpClient;
    @Getter
    private CookieManager cookieManager;
    @Getter
    private String deviceId;
    @Getter
    private String guid;
    @Getter
    private String phoneId;
    @Getter
    private boolean loggedIn = false;
    @Getter
    private IGLoggedInUser selfUser;

    // logging
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor((msg) -> {
        log.debug(msg);
    }).setLevel(Level.BODY);

    public IGClient(String username, String password) {
        this(username, password, new CookieManager(), new OkHttpClient.Builder());
    }

    public IGClient(String username, String password, CookieManager manager, OkHttpClient.Builder httpBuilder) {
        this.username = username;
        this.password = password;
        this.guid = IGUtils.randomUuid();
        this.phoneId = IGUtils.randomUuid();
        this.deviceId = IGUtils.generateDeviceId(username, password);
        this.cookieManager = manager;
        this.httpClient = httpBuilder
                .cookieJar(new JavaNetCookieJar(this.cookieManager))
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @SneakyThrows
    public IGLoginResponse sendLoginRequest() throws IGLoginException {
        log.debug("Logging in. . .");
        IGLoginRequest login = new IGLoginRequest(username, password);
        IGLoginResponse res = this.sendRequest(login);
        log.debug("Response is : " + res.getStatus());
        this.setLoggedInState(res);

        return res;
    }

    @SneakyThrows
    public IGLoginResponse sendLoginRequest(String code, String identifier) throws IGLoginException {
        log.debug("Logging in. . .");
        IGTwoFactorLoginRequest login = new IGTwoFactorLoginRequest(username, password, code, identifier);
        IGLoginResponse res = this.sendRequest(login);
        log.debug("Response is : " + res.getStatus());
        this.setLoggedInState(res);

        return res;
    }

    @SneakyThrows
    public <T extends IGResponse> T sendRequest(IGRequest<T> req) throws IGResponseException {
        return sendRequestWithView(req, req.getResponseType());
    }

    @SneakyThrows
    public <T> T sendRequestWithView(IGRequest<?> req, Class<T> view) throws IGResponseException {
        req.setClient(this);
        Response res = httpClient.newCall(req.formRequest()).execute();

        try (ResponseBody body = res.body()) {
            return req.parseResponse(body.string(), view);
        } catch (NullPointerException ex) {
            throw new IGResponseException(IGExceptionInfo.builder().response(res).build(), "Empty body received", ex);
        }
    }

    private void setLoggedInState(IGLoginResponse state) throws IGLoginException {
        if (!state.getStatus().equals("ok"))
            throw new IGLoginException(state);
        this.loggedIn = true;
        this.selfUser = state.getLogged_in_user();
    }

    public String getCsrfToken() {
        return IGUtils.getCookieValue(this.cookieManager.getCookieStore(), "csrftoken").orElse("missing");
    }

    public IGPayload setIGLoad(IGPayload load) {
        load.set_csrftoken(this.getCsrfToken());
        load.setDevice_id(this.deviceId);
        load.setGuid(this.guid);
        load.setPhone_id(this.phoneId);

        return load;
    }

    public static IGClient.Builder builder() {
        return new IGClient.Builder();
    }

    @With
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Builder {
        private String username;
        private String password;
        private CookieManager cookieManager = new CookieManager();
        private OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        private LoginHandler onChallenge;
        private LoginHandler onTwoFactor;

        public static IGClient.Builder from(IGClient client) {
            return new IGClient.Builder()
                    .withUsername(client.username)
                    .withPassword(client.password)
                    .withCookieManager(client.cookieManager);
        }

        public IGClient login() throws IGLoginException, IGChallengeException {
            IGClient client = new IGClient(username, password, cookieManager, clientBuilder);

            try {
                client.sendLoginRequest();
            } catch (IGLoginException ex) {
                if (ex.getResponse().getTwo_factor_info() != null && onTwoFactor != null)
                    client.setLoggedInState(onTwoFactor.accept(client, ex.getResponse()));
                else if (ex.getResponse().getChallenge() != null && onChallenge != null)
                    client.setLoggedInState(onChallenge.accept(client, ex.getResponse()));
                else
                    throw new IGLoginException(ex.getResponse());
            }

            return client;
        }

        @FunctionalInterface
        public static interface LoginHandler {
            public IGLoginResponse accept(IGClient client, IGLoginResponse t)
                    throws IGLoginException, IGChallengeException;
        }
    }
}