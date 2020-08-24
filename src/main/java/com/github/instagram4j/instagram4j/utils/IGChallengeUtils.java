package com.github.instagram4j.instagram4j.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException.IGFailedResponse;
import com.github.instagram4j.instagram4j.requests.challenge.ChallengeResetRequest;
import com.github.instagram4j.instagram4j.requests.challenge.ChallengeSelectVerifyMethodRequest;
import com.github.instagram4j.instagram4j.requests.challenge.ChallengeSendCodeRequest;
import com.github.instagram4j.instagram4j.requests.challenge.ChallengeStateGetRequest;
import com.github.instagram4j.instagram4j.responses.accounts.LoginResponse;
import com.github.instagram4j.instagram4j.responses.challenge.Challenge;
import com.github.instagram4j.instagram4j.responses.challenge.ChallengeStateResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IGChallengeUtils {

    private static LoginResponse handleException(Throwable t) {
        log.info(t.getCause().toString());
        return IGFailedResponse.of(t.getCause(), LoginResponse.class);
    }

    public static CompletableFuture<ChallengeStateResponse> requestState(IGClient client,
            Challenge challenge) {
        return new ChallengeStateGetRequest(challenge.getApi_path(), client.getGuid(),
                client.getDeviceId())
                        .execute(client);
    }

    public static CompletableFuture<ChallengeStateResponse> selectVerifyMethod(IGClient client,
            Challenge challenge,
            String method,
            boolean resend) {
        return new ChallengeSelectVerifyMethodRequest(challenge.getApi_path(), method, resend)
                .execute(client);
    }

    public static CompletableFuture<LoginResponse> selectVerifyMethodDelta(IGClient client,
            Challenge challenge,
            String method,
            boolean resend) {
        return new ChallengeSelectVerifyMethodRequest(challenge.getApi_path(), method, resend)
                .execute(client)
                .thenApply(res -> IGUtils.convertToView(res, LoginResponse.class));
    }

    public static CompletableFuture<LoginResponse> sendSecurityCode(IGClient client,
            Challenge challenge, String code) {
        return new ChallengeSendCodeRequest(challenge.getApi_path(), code).execute(client);
    }

    public static CompletableFuture<ChallengeStateResponse> resetChallenge(IGClient client,
            Challenge challenge) {
        return new ChallengeResetRequest(challenge.getApi_path()).execute(client);
    }

    public static LoginResponse resolveChallenge(@NonNull IGClient client,
            @NonNull LoginResponse response,
            @NonNull Callable<String> inputCode, int retries) {
        Challenge challenge = response.getChallenge();
        ChallengeStateResponse stateResponse = requestState(client, challenge).join();
        String name = stateResponse.getStep_name();

        if (name.equalsIgnoreCase("select_verify_method")) {
            // verify by phone or email
            selectVerifyMethod(client, challenge, stateResponse.getStep_data().getChoice(), false);
            log.info("select_verify_method option security code sent to "
                    + (stateResponse.getStep_data().getChoice().equals("1") ? "email" : "phone"));
            do {
                try {
                    response = sendSecurityCode(client, challenge, inputCode.call())
                            .exceptionally(IGChallengeUtils::handleException)
                            .join();
                } catch (Exception e) {
                    log.info(e.toString());
                }
            } while (!response.getStatus().equalsIgnoreCase("ok") && --retries > 0);
        } else if (name.equalsIgnoreCase("delta_login_review")) {
            // 'This was me' option
            log.info("delta_login_review option sent choice 0");
            response = selectVerifyMethodDelta(client, challenge, "0", false)
                    .exceptionally(IGChallengeUtils::handleException)
                    .join();
        } else {
            // Unknown step_name
        }

        return response;

    }

    public static LoginResponse resolveChallenge(@NonNull IGClient client,
            @NonNull LoginResponse response,
            @NonNull Callable<String> inputCode) {
        return resolveChallenge(client, response, inputCode, 3);
    }

    public static LoginResponse resolveTwoFactor(@NonNull IGClient client,
            @NonNull LoginResponse response,
            @NonNull Callable<String> inputCode) {
        return resolveTwoFactor(client, response, inputCode, 3);
    }

    public static LoginResponse resolveTwoFactor(@NonNull IGClient client,
            @NonNull LoginResponse response,
            @NonNull Callable<String> inputCode, int retries) {
        String identifier = response.getTwo_factor_info().getTwo_factor_identifier();
        do {
            try {
                String code = inputCode.call();

                response = client.sendLoginRequest(code, identifier)
                        .exceptionally(IGChallengeUtils::handleException)
                        .join();
            } catch (Exception e) {
                log.info(e.toString());
            }
        } while (!response.getStatus().equals("ok") && --retries > 0);

        return response;
    }
}
