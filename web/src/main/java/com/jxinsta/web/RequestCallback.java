package com.jxinsta.web;

import okhttp3.ResponseBody;

/**
 * Interface for handling asynchronous request results.
 */
public interface RequestCallback {
    /**
     * Called when the request is successful.
     *
     * @param response The response body from the successful request.
     */
    void onSuccess(ResponseBody response);

    /**
     * Called when the request fails.
     *
     * @param e The exception that caused the failure.
     */
    void onFailure(Exception e);
}
