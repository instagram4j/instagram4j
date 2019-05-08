/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.brunocvcunha.instagram4j.requests.InstagramAutoCompleteUserListRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetInboxRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetRecentActivityRequest;
import org.brunocvcunha.instagram4j.requests.InstagramLoginRequest;
import org.brunocvcunha.instagram4j.requests.InstagramLoginTwoFactorRequest;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;
import org.brunocvcunha.instagram4j.requests.InstagramTimelineFeedRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramFetchHeadersRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramLogAttributionRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramReadMsisdnHeaderRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramSyncFeaturesRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramZeroRatingTokenRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginPayload;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginTwoFactorPayload;
import org.brunocvcunha.instagram4j.util.InstagramGenericUtil;
import org.brunocvcunha.instagram4j.util.InstagramHashUtil;
import org.brunocvcunha.inutils4j.MyNumberUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

/**
 * 
 * Instagram4j API
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Log4j
public class Instagram4j implements Serializable {
    
    @Getter
    protected String deviceId;
    
    @Getter
    protected String uuid;
    
    @Getter
    protected String advertisingId;
    
    @Getter @Setter
    protected String username;
    
    @Getter @Setter
    protected String password;

    @Getter
    protected CredentialsProvider credentialsProvider;

    @Getter @Setter
    protected HttpHost proxy;
    
    @Getter @Setter
    protected long userId;
    
    @Getter @Setter
    protected String rankToken;
    
    @Getter
    protected boolean isLoggedIn;

    @Getter @Setter
    protected HttpResponse lastResponse;
    
    @Getter @Setter
    protected boolean debug;
    
    @Getter
    protected CookieStore cookieStore;

    @Getter
    protected CloseableHttpClient client;

    protected String identifier;
    protected String verificationCode;
    protected String challengeUrl;

    /**
     * @param username Username
     * @param password Password
     */
    public Instagram4j(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    
    /**
     * @param username Username
     * @param password Password
     * @param userId UserId
     * @param uuid UUID
     * @param cookieStore Cookie Store
     * @param proxy proxy
     * @param credentialsProvider proxy credential
     */
    @Builder
    public Instagram4j(String username, String password, long userId, String uuid, CookieStore cookieStore, HttpHost proxy, CredentialsProvider credentialsProvider) {
        super();
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.uuid = uuid;
        this.cookieStore = cookieStore;
        this.proxy = proxy;
        this.credentialsProvider = credentialsProvider;
        this.isLoggedIn = true;
    }
    
    /**
     * Setup some variables
     */
    public void setup() {
        log.info("Setup...");
        
        if (StringUtils.isEmpty(this.username)) {
            throw new IllegalArgumentException("Username is mandatory.");
        }
        
        if (StringUtils.isEmpty(this.password)) {
            throw new IllegalArgumentException("Password is mandatory.");
        }
        
        this.deviceId = InstagramHashUtil.generateDeviceId(this.username, this.password);
        
        if (StringUtils.isEmpty(this.uuid)) {
            this.uuid = InstagramGenericUtil.generateUuid(true);
        }
        
        if (StringUtils.isEmpty(this.advertisingId)) {
            this.advertisingId = InstagramGenericUtil.generateUuid(true);
        }
        
        if (this.cookieStore == null) {
            this.cookieStore = new BasicCookieStore();
        }
        
        log.info("Device ID is: " + this.deviceId + ", random id: " + this.uuid);
        HttpClientBuilder builder = HttpClientBuilder.create();
        if (proxy != null) {
            builder.setProxy(proxy);
        }

        if(credentialsProvider != null)
            builder.setDefaultCredentialsProvider(credentialsProvider);

        builder.setDefaultCookieStore(this.cookieStore);
        this.client = builder.build();
    }

    /**
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public InstagramLoginResult login() throws ClientProtocolException, IOException {

        log.info("Logging with user " + username + " and password " + password.replaceAll("[a-zA-Z0-9]", "*"));


        this.sendRequest(new InstagramReadMsisdnHeaderRequest());
        this.sendRequest(new InstagramSyncFeaturesRequest(true));
        this.sendRequest(new InstagramZeroRatingTokenRequest());
        this.sendRequest(new InstagramLogAttributionRequest());

        
        InstagramLoginPayload loginRequest = InstagramLoginPayload.builder().username(username)
                .password(password)
                .guid(uuid)
                .device_id(deviceId)
                .phone_id(InstagramGenericUtil.generateUuid(true))
                .login_attempt_account(0)
                ._csrftoken(getOrFetchCsrf())
                .build();
        InstagramLoginRequest req = new InstagramLoginRequest(loginRequest);
        InstagramLoginResult loginResult = this.sendRequest(req);
        emulateUserLoggedIn(loginResult);

        if (loginResult.getTwo_factor_info() != null) {
            identifier = loginResult.getTwo_factor_info().getTwo_factor_identifier();
        } else if (loginResult.getChallenge() != null) {
            // logic for challenge
            log.info("Challenge required: " + loginResult.getChallenge());
        }
        
        return loginResult;
    }

    public InstagramLoginResult login(String verificationCode) throws ClientProtocolException, IOException {
        if (identifier == null) {
            login();
        }
        InstagramLoginTwoFactorPayload loginRequest = InstagramLoginTwoFactorPayload.builder().username(username)
                .verification_code(verificationCode)
                .two_factor_identifier(identifier)
                .password(password)
                .guid(uuid)
                .device_id(deviceId)
                .phone_id(InstagramGenericUtil.generateUuid(true))
                .login_attempt_account(0)
                ._csrftoken(getOrFetchCsrf())
                .build();
        InstagramLoginTwoFactorRequest req = new InstagramLoginTwoFactorRequest(loginRequest);
        InstagramLoginResult loginResult = this.sendRequest(req);
        emulateUserLoggedIn(loginResult);
        return loginResult;
    }

    /**
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String getOrFetchCsrf() throws ClientProtocolException, IOException {
        Optional<Cookie> checkCookie = getCsrfCookie();
        if (!checkCookie.isPresent()) {
            sendRequest(new InstagramFetchHeadersRequest());
            checkCookie = getCsrfCookie();
        }
        String csrfToken = checkCookie.get().getValue();
        return csrfToken;
    }
    
    public Optional<Cookie> getCsrfCookie() {
        return cookieStore.getCookies().stream().filter(cookie -> cookie.getName().equalsIgnoreCase("csrftoken")).findFirst();
    }
    /**
     * Send request to endpoint
     * @param request Request object
     * @return success flag
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public <T> T sendRequest(InstagramRequest<T> request) throws ClientProtocolException, IOException {
        
        log.info("Sending request: " + request.getClass().getName());

        if (!this.isLoggedIn
                && request.requiresLogin()) {
            throw new IllegalStateException("Need to login first!");
        }
        
        // wait to simulate real human interaction
        randomWait();
        
        request.setApi(this);
        T response = request.execute();
        
        log.debug("Result for " + request.getClass().getName() + ": " + response);
        
        return response;
    }

    private void emulateUserLoggedIn(InstagramLoginResult loginResult) throws IOException {
        if (loginResult.getStatus().equalsIgnoreCase("ok")) {
            this.userId = loginResult.getLogged_in_user().getPk();
            this.rankToken = this.userId + "_" + this.uuid;
            this.isLoggedIn = true;

            this.sendRequest(new InstagramSyncFeaturesRequest(false));
            this.sendRequest(new InstagramAutoCompleteUserListRequest());
            this.sendRequest(new InstagramTimelineFeedRequest());
            this.sendRequest(new InstagramGetInboxRequest());
            this.sendRequest(new InstagramGetRecentActivityRequest());
        }
    }
    
    @SneakyThrows
    private void randomWait() {
        Thread.sleep(MyNumberUtils.randomLongBetween(100, 250));
    }
}