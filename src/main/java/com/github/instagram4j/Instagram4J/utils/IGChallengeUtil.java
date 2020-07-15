package com.github.instagram4j.Instagram4J.utils;

import java.util.concurrent.Callable;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.exceptions.IGChallengeException;
import com.github.instagram4j.Instagram4J.exceptions.IGLoginException;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException;
import com.github.instagram4j.Instagram4J.requests.challenge.IGChallengeResetRequest;
import com.github.instagram4j.Instagram4J.requests.challenge.IGChallengeSelectVerifyMethodRequest;
import com.github.instagram4j.Instagram4J.requests.challenge.IGChallengeSendCodeRequest;
import com.github.instagram4j.Instagram4J.requests.challenge.IGChallengeStateGetRequest;
import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;
import com.github.instagram4j.Instagram4J.responses.challenge.IGChallenge;
import com.github.instagram4j.Instagram4J.responses.challenge.IGChallengeStateResponse;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IGChallengeUtil {

    public static IGChallengeStateResponse requestState(IGClient client, IGChallenge challenge)
            throws IGResponseException {
        return client.sendRequest(
                new IGChallengeStateGetRequest(challenge.getApi_path(), client.getGuid(), client.getDeviceId()));
    }

    public static IGChallengeStateResponse selectVerifyMethod(IGClient client, IGChallenge challenge, String method,
            boolean resend) throws IGResponseException {
        return client.sendRequest(new IGChallengeSelectVerifyMethodRequest(challenge.getApi_path(), method, resend));
    }
    
    public static IGLoginResponse selectVerifyMethodDelta(IGClient client, IGChallenge challenge, String method,
            boolean resend) throws IGResponseException {
        return client.sendRequest(new IGChallengeSelectVerifyMethodRequest(challenge.getApi_path(), method, resend), IGLoginResponse.class);
    }

    public static IGLoginResponse sendSecurityCode(IGClient client, IGChallenge challenge, String code)
            throws IGResponseException {
        return client.sendRequest(new IGChallengeSendCodeRequest(challenge.getApi_path(), code));
    }

    public static IGChallengeStateResponse resetChallenge(IGClient client, IGChallenge challenge)
            throws IGResponseException {
        return client.sendRequest(new IGChallengeResetRequest(challenge.getApi_path()));
    }

    @SneakyThrows
    public static IGLoginResponse resolve(@NonNull IGClient client, @NonNull IGChallenge challenge, @NonNull Callable<String> inputCode, int retries)
            throws IGChallengeException {
        IGChallengeStateResponse stateResponse = requestState(client, challenge);
        String name = stateResponse.getStep_name();

        if (name.equalsIgnoreCase("select_verify_method")) {
            selectVerifyMethod(client, challenge, stateResponse.getStep_data().getChoice(), false);
            log.info("select_verify_method option security code sent to "
                    + (stateResponse.getStep_data().getChoice().equals("1") ? "email" : "phone"));
        } else if (name.equalsIgnoreCase("delta_login_review")) {
            log.info("delta_login_review option sent choice 0");
            return selectVerifyMethodDelta(client, challenge, "0", false);
        } else {
            throw new IGChallengeException("Unknown step_name");
        }

        IGLoginResponse loginResponse = sendSecurityCode(client, challenge, inputCode.call());
        while (!loginResponse.getStatus().equalsIgnoreCase("ok") && --retries > 0) {
            log.info("Wrong security code");
            loginResponse = sendSecurityCode(client, challenge, inputCode.call());
        }

        return loginResponse;
    }
    
    @SneakyThrows
    public static IGLoginResponse resolve(@NonNull IGClient client, @NonNull IGChallenge challenge, @NonNull Callable<String> inputCode)
            throws IGChallengeException {
        return resolve(client, challenge, inputCode, 3);
    }

    @SneakyThrows
    public static IGLoginResponse resolve(IGLoginException ex, Callable<String> inputCode)
            throws IGLoginException, IGChallengeException {
        return resolve(ex.getClient(), ex.getLoginResponse().getChallenge(), inputCode);
    }
    
    @SneakyThrows
    public static IGLoginResponse resolve(IGLoginException ex, Callable<String> inputCode, int retries)
            throws IGLoginException, IGChallengeException {
        return resolve(ex.getClient(), ex.getLoginResponse().getChallenge(), inputCode, retries);
    }
    
    @SneakyThrows
    public static IGLoginResponse resolveTwoFactor(@NonNull IGClient client, @NonNull IGLoginResponse response, @NonNull Callable<String> inputCode) {
        return resolveTwoFactor(client, response, inputCode, 3);
    }
    
    @SneakyThrows
    public static IGLoginResponse resolveTwoFactor(@NonNull IGClient client, @NonNull IGLoginResponse response, @NonNull Callable<String> inputCode, int retries) {
        do {
            String code = inputCode.call();

            try {
                response = client.sendLoginRequest(code, response.getTwo_factor_info().getTwo_factor_identifier());
            } catch (IGLoginException | IGResponseException e) {
                log.info(e.getMessage());
            }
        } while (!response.getStatus().equals("ok") && --retries > 0);
        
        return response;
    }
}