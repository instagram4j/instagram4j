package com.jxinsta.mobile;

import org.jetbrains.annotations.NotNull;

import android.org.json.JSONObject;

/**
 * Exception class for errors returned by the Instagram mobile API or library-specific failures.
 * This class categorizes errors using the {@link Reasons} enum.
 */
public class InstagramException extends Exception {
    private final Reasons reason;

    /**
     * Constructs a new InstagramException with the specified detail message and reason.
     *
     * @param message The detail message.
     * @param reasons The categorized reason for the exception.
     */
    public InstagramException(String message, Reasons reasons) {
        super(message);
        this.reason = reasons;
    }

    /**
     * Wraps a JSON error response and HTTP status code into an InstagramException.
     * This method maps specific Instagram error codes and messages to categorized {@link Reasons}.
     *
     * @param lastJson   The JSON object containing the error response from Instagram.
     * @param statusCode The HTTP status code of the response.
     * @return An {@link InstagramException} with a populated reason.
     */
    public static InstagramException wrap(@NotNull JSONObject lastJson, int statusCode) {
        String message = lastJson.optString("message", "");
        String errorType = lastJson.optString("error_type", "");

        if (message != null && message.contains("Please wait a few minutes")) {
            return new InstagramException("PleaseWaitFewMinutes: " + lastJson.toString(), Reasons.RATE_LIMITED);
        }

        if (statusCode == 403) {
            if ("login_required".equals(message)) {
                return new InstagramException("LoginRequired: " + lastJson.toString(), Reasons.LOGIN_EXPIRED);
            }
            return new InstagramException("ClientForbiddenError: " + lastJson.toString(), Reasons.FORBIDDEN);
        }

        if (statusCode == 400) {
            if (lastJson.has("two_factor_info") && !lastJson.isNull("two_factor_info")) {
                if (message == null || message.isEmpty()) {
                    lastJson.put("message", "Two-factor authentication required");
                }
                if (!"two_factor_required".equals(errorType)) {
                    lastJson.put("error_type", "two_factor_required");
                }
                return new InstagramException("TwoFactorRequired: " + lastJson.toString(), Reasons.TWO_FACTOR_REQUIRED);
            }

            if ("challenge_required".equals(message)) {
                return new InstagramException("ChallengeRequired: " + lastJson.toString(), Reasons.CHECKPOINT_REQUIRED);
            }
            if ("feedback_required".equals(message)) {
                String feedback = lastJson.optString("feedback_message", "");
                lastJson.put("message", message + ": " + feedback);
                return new InstagramException("FeedbackRequired: " + lastJson.toString(), Reasons.FEEDBACK_REQUIRED);
            }

            if ("sentry_block".equals(errorType)) {
                return new InstagramException("SentryBlock: " + lastJson.toString(), Reasons.SENTRY_BLOCK);
            }
            if ("rate_limit_error".equals(errorType)) {
                return new InstagramException("RateLimitError: " + lastJson.toString(), Reasons.RATE_LIMITED);
            }
            if ("bad_password".equals(errorType)) {
                String msg = lastJson.optString("message", "").trim();
                if (!msg.isEmpty() && !msg.endsWith(".")) {
                    msg = msg + ".";
                }
                if (!msg.isEmpty()) {
                    msg = msg + " ";
                }
                lastJson.put("message",
                        msg + "If you are sure that the password is correct, then change your IP address, because it is added to the blacklist of the Instagram Server");
                return new InstagramException("BadPassword: " + lastJson.toString(), Reasons.INCORRECT_PASSWORD);
            }
            if ("two_factor_required".equals(errorType)) {
                if (message == null || message.isEmpty()) {
                    lastJson.put("message", "Two-factor authentication required");
                }
                return new InstagramException("TwoFactorRequired: " + lastJson.toString(), Reasons.TWO_FACTOR_REQUIRED);
            }

            if (message != null && message.contains("VideoTooLongException")) {
                return new InstagramException("VideoTooLongException: " + lastJson.toString(), Reasons.VIDEO_TOO_LONG);
            }
            if (message != null && message.contains("Not authorized to view user")) {
                return new InstagramException("PrivateAccount: " + lastJson.toString(), Reasons.PRIVATE_ACCOUNT);
            }
            if (message != null && message.contains("Invalid target user")) {
                return new InstagramException("InvalidTargetUser: " + lastJson.toString(), Reasons.INVALID_TARGET_USER);
            }
            if (message != null && message.contains("Invalid media_id")) {
                return new InstagramException("InvalidMediaId: " + lastJson.toString(), Reasons.INVALID_MEDIA_ID);
            }
            if (message != null && (message.contains("Media is unavailable")
                    || message.contains("Media not found or unavailable")
                    || message.contains("has been deleted"))) {
                return new InstagramException("MediaUnavailable: " + lastJson.toString(), Reasons.MEDIA_UNAVAILABLE);
            }
            if (message != null && message.contains("unable to fetch followers")) {
                return new InstagramException("UserNotFound: " + lastJson.toString(), Reasons.USER_NOT_FOUND);
            }
            if (message != null && message.contains("The username you entered")) {
                lastJson.put("message", "Instagram has blocked your IP address, use a quality proxy provider (not free, not shared)");
                return new InstagramException("ProxyAddressIsBlocked: " + lastJson.toString(), Reasons.PROXY_ADDRESS_BLOCKED);
            }

            // fallback: any error_type or message -> UnknownError
            if ((errorType != null && !errorType.isEmpty()) || (message != null && !message.isEmpty())) {
                return new InstagramException("UnknownError: " + lastJson.toString(), Reasons.UNKNOWN);
            }

            // final fallback for 400
            String warnMsg = "Empty response message. Maybe enabled Two-factor auth?";
            return new InstagramException("ClientBadRequestError: " + warnMsg + " | " + lastJson.toString(), Reasons.BAD_REQUEST);
        }

        if (statusCode == 429) {
            return new InstagramException("ClientThrottledError: Too many requests | " + lastJson.toString(), Reasons.THROTTLED);
        }

        if (statusCode == 404) {
            return new InstagramException("ClientNotFoundError: Endpoint does not exist | " + lastJson.toString(), Reasons.NOT_FOUND);
        }

        if (statusCode == 408) {
            return new InstagramException("ClientRequestTimeout: Request Timeout | " + lastJson.toString(), Reasons.REQUEST_TIMEOUT);
        }

        if (statusCode >= 400) {
            return new InstagramException("ClientError: HTTP " + statusCode + " | " + lastJson.toString(), Reasons.UNKNOWN);
        }

        if ("fail".equals(lastJson.optString("status", null))) {
            return new InstagramException("ClientError: status=fail | " + lastJson.toString(), Reasons.UNKNOWN);
        }
        if (lastJson.has("error_title")) {
            return new InstagramException("ClientError: error_title present | " + lastJson.toString(), Reasons.UNKNOWN);
        }

        return new InstagramException("UnknownError: " + lastJson.toString(), Reasons.UNKNOWN);
    }

    /**
     * Returns the categorized reason for this exception.
     *
     * @return The {@link Reasons} enum constant.
     */
    public Reasons getReason() {
        return reason;
    }

    /**
     * Enum containing specific reasons for an {@link InstagramException}.
     */
    public enum Reasons {
        /** An unknown error occurred. */
        UNKNOWN, 
        /** An unknown error occurred during login. */
        UNKNOWN_LOGIN_ERROR,
        /** Invalid credentials provided. */
        INVALID_CREDENTIAL, 
        /** Invalid login type. */
        INVALID_LOGIN_TYPE, 
        /** Incorrect password provided. */
        INCORRECT_PASSWORD, 
        /** Incorrect username provided. */
        INCORRECT_USERNAME,
        /** The session/login has expired. */
        LOGIN_EXPIRED, 
        /** A checkpoint (security challenge) is required. */
        CHECKPOINT_REQUIRED, 
        /** Too many requests (rate limited). */
        RATE_LIMITED, 
        /** Two-factor authentication is required. */
        TWO_FACTOR_REQUIRED,
        /** CSRF token authentication failed. */
        CSRF_AUTHENTICATION_FAILED, 
        /** Network I/O error. */
        IO, 
        /** JSON parsing error. */
        PARSING_FAILED,
        /** Access forbidden. */
        FORBIDDEN, 
        /** Feedback is required by Instagram. */
        FEEDBACK_REQUIRED, 
        /** Blocked by Instagram's Sentry system. */
        SENTRY_BLOCK,
        /** Uploaded video is too long. */
        VIDEO_TOO_LONG, 
        /** The target account is private. */
        PRIVATE_ACCOUNT, 
        /** Invalid target user specified. */
        INVALID_TARGET_USER, 
        /** Invalid media ID specified. */
        INVALID_MEDIA_ID,
        /** The requested media is unavailable or deleted. */
        MEDIA_UNAVAILABLE, 
        /** The specified user was not found. */
        USER_NOT_FOUND, 
        /** The proxy IP address is blocked by Instagram. */
        PROXY_ADDRESS_BLOCKED,
        /** Bad request sent to the server. */
        BAD_REQUEST, 
        /** Request throttled. */
        THROTTLED, 
        /** Requested resource not found. */
        NOT_FOUND, 
        /** The request timed out. */
        REQUEST_TIMEOUT
    }
}
