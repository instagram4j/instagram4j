package com.github.instagram4j.Instagram4J.utils;

import java.util.concurrent.Callable;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.exceptions.ChallengeException;
import com.github.instagram4j.Instagram4J.exceptions.LoginException;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException;
import com.github.instagram4j.Instagram4J.requests.challenge.ChallengeResetRequest;
import com.github.instagram4j.Instagram4J.requests.challenge.ChallengeSelectVerifyMethodRequest;
import com.github.instagram4j.Instagram4J.requests.challenge.ChallengeSendCodeRequest;
import com.github.instagram4j.Instagram4J.requests.challenge.ChallengeStateGetRequest;
import com.github.instagram4j.Instagram4J.responses.accounts.LoginResponse;
import com.github.instagram4j.Instagram4J.responses.challenge.Challenge;
import com.github.instagram4j.Instagram4J.responses.challenge.ChallengeStateResponse;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChallengeUtil {

    public static ChallengeStateResponse requestState(IGClient client, Challenge challenge)
            throws IGResponseException {
        return client.sendRequest(
                new ChallengeStateGetRequest(challenge.getApi_path(), client.getGuid(), client.getDeviceId()));
    }

    public static ChallengeStateResponse selectVerifyMethod(IGClient client, Challenge challenge, String method,
            boolean resend) throws IGResponseException {
        return client.sendRequest(new ChallengeSelectVerifyMethodRequest(challenge.getApi_path(), method, resend));
    }
    
    public static LoginResponse selectVerifyMethodDelta(IGClient client, Challenge challenge, String method,
            boolean resend) throws IGResponseException {
        return client.sendRequest(new ChallengeSelectVerifyMethodRequest(challenge.getApi_path(), method, resend), LoginResponse.class);
    }

    public static LoginResponse sendSecurityCode(IGClient client, Challenge challenge, String code)
            throws IGResponseException {
        return client.sendRequest(new ChallengeSendCodeRequest(challenge.getApi_path(), code));
    }

    public static ChallengeStateResponse resetChallenge(IGClient client, Challenge challenge)
            throws IGResponseException {
        return client.sendRequest(new ChallengeResetRequest(challenge.getApi_path()));
    }

    @SneakyThrows
    public static LoginResponse resolve(@NonNull IGClient client, @NonNull Challenge challenge, @NonNull Callable<String> inputCode, int retries)
            throws ChallengeException {
        ChallengeStateResponse stateResponse = requestState(client, challenge);
        String name = stateResponse.getStep_name();

        if (name.equalsIgnoreCase("select_verify_method")) {
            selectVerifyMethod(client, challenge, stateResponse.getStep_data().getChoice(), false);
            log.info("select_verify_method option security code sent to "
                    + (stateResponse.getStep_data().getChoice().equals("1") ? "email" : "phone"));
        } else if (name.equalsIgnoreCase("delta_login_review")) {
            log.info("delta_login_review option sent choice 0");
            return selectVerifyMethodDelta(client, challenge, "0", false);
        } else {
            throw new ChallengeException("Unknown step_name");
        }

        LoginResponse loginResponse = sendSecurityCode(client, challenge, inputCode.call());
        while (!loginResponse.getStatus().equalsIgnoreCase("ok") && --retries > 0) {
            log.info("Wrong security code");
            loginResponse = sendSecurityCode(client, challenge, inputCode.call());
        }

        return loginResponse;
    }
    
    @SneakyThrows
    public static LoginResponse resolve(@NonNull IGClient client, @NonNull Challenge challenge, @NonNull Callable<String> inputCode)
            throws ChallengeException {
        return resolve(client, challenge, inputCode, 3);
    }

    @SneakyThrows
    public static LoginResponse resolve(LoginException ex, Callable<String> inputCode)
            throws LoginException, ChallengeException {
        return resolve(ex.getClient(), ex.getLoginResponse().getChallenge(), inputCode);
    }
    
    @SneakyThrows
    public static LoginResponse resolve(LoginException ex, Callable<String> inputCode, int retries)
            throws LoginException, ChallengeException {
        return resolve(ex.getClient(), ex.getLoginResponse().getChallenge(), inputCode, retries);
    }
    
    @SneakyThrows
    public static LoginResponse resolveTwoFactor(@NonNull IGClient client, @NonNull LoginResponse response, @NonNull Callable<String> inputCode) {
        return resolveTwoFactor(client, response, inputCode, 3);
    }
    
    @SneakyThrows
    public static LoginResponse resolveTwoFactor(@NonNull IGClient client, @NonNull LoginResponse response, @NonNull Callable<String> inputCode, int retries) {
        do {
            String code = inputCode.call();

            try {
                response = client.sendLoginRequest(code, response.getTwo_factor_info().getTwo_factor_identifier());
            } catch (LoginException e) {
                response = e.getLoginResponse();
                log.info(e.getMessage());
            }
        } while (!response.getStatus().equals("ok") && --retries > 0);
        
        return response;
    }
}