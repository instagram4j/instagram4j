package com.jxinsta.mobile.utils;

import okhttp3.ResponseBody;

/**
 * Interface for handling asynchronous request results in the mobile module.
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
