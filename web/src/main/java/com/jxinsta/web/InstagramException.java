package com.jxinsta.web;

import org.jetbrains.annotations.NotNull;
import android.org.json.JSONObject;

public class InstagramException extends Exception {
    private final Reasons reason;

    public InstagramException(String message, Reasons reasons) {
        super(message);
        this.reason = reasons;
    }

    public InstagramException(String message) {
        super(message);
        this.reason = Reasons.UNKNOWN;
    }

    public static InstagramException wrap(@NotNull JSONObject lastJson, int statusCode) {
        String message = lastJson.optString("message", "");
        String errorType = lastJson.optString("error_type", "");

        if (message.contains("Please wait a few minutes")) {
            return new InstagramException("RateLimited: " + lastJson, Reasons.RATE_LIMITED);
        }

        if (statusCode == 403) {
            if ("login_required".equals(message)) {
                return new InstagramException("LoginRequired: " + lastJson, Reasons.LOGIN_EXPIRED);
            }
            return new InstagramException("Forbidden: " + lastJson, Reasons.UNKNOWN);
        }

        if (statusCode == 400) {
            if ("challenge_required".equals(message)) {
                return new InstagramException("ChallengeRequired: " + lastJson, Reasons.CHECKPOINT_REQUIRED);
            }
            if (message.contains("The password you entered is incorrect")) {
                return new InstagramException("IncorrectPassword: " + lastJson, Reasons.INCORRECT_PASSWORD);
            }
            if (message.contains("The username you entered doesn't appear to belong to an account")) {
                return new InstagramException("IncorrectUsername: " + lastJson, Reasons.INCORRECT_USERNAME);
            }
            if (message.contains("CSRF token missing or incorrect")) {
                return new InstagramException("CSRFMissing: " + lastJson, Reasons.CSRF_AUTHENTICATION_FAILED);
            }
        }

        if (statusCode == 429) {
            return new InstagramException("Throttled: " + lastJson, Reasons.RATE_LIMITED);
        }

        return new InstagramException("UnknownError: " + lastJson, Reasons.UNKNOWN);
    }

    public Reasons getReason() {
        return reason;
    }

    public enum Reasons {
        UNKNOWN, UNKNOWN_LOGIN_ERROR,
        INVALID_CREDENTIAL, INVALID_LOGIN_TYPE, INCORRECT_PASSWORD, INCORRECT_USERNAME,
        LOGIN_EXPIRED, CHECKPOINT_REQUIRED, RATE_LIMITED, TWO_FACTOR_REQUIRED,
        CSRF_AUTHENTICATION_FAILED, IO, PARSING_FAILED,
    }
}
