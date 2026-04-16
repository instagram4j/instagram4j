package com.jxinsta.mobile.utils;


import com.jxinsta.mobile.InstagramException;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.org.json.JSONObject;

/**
 * Utility class containing helper methods for network requests, encoding, and common Instagram mobile API operations.
 */
public class Utils {
    private static final String ENCODING_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    /**
     * Shared OkHttpClient instance for all mobile network calls.
     */
    public static OkHttpClient client = new OkHttpClient().newBuilder().build();

    /**
     * Generates a random device ID for Instagram API requests.
     *
     * @return A random device ID string.
     */
    public static String getDeviceId() {
        return "android-" + Math.random();
    }

    /**
     * Executes an HTTP request and parses the response into a JSONObject.
     *
     * @param request The OkHttp Request to execute.
     * @return A {@link JSONObject} containing the response data.
     * @throws InstagramException If the API returns an error or a network failure occurs.
     */
    public static JSONObject callAPI(Request request) throws InstagramException {
        try (Response res = client.newCall(request).execute()) {
            var body = res.body().string();
            var json = new JSONObject(body);
            if (res.isSuccessful()){
                return json;
            } else {
                throw InstagramException.wrap(json, res.code());
            }
        } catch (IOException e) {
            throw new InstagramException(e.getMessage(), InstagramException.Reasons.IO);
        }
    }

    /**
     * Executes an HTTP request asynchronously.
     *
     * @param request  The OkHttp Request to execute.
     * @param callback The callback to handle the result.
     */
    public static void callAsync(Request request, RequestCallback callback) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                callback.onSuccess(response.body());
            }
        });
    }

    /**
     * Performs a public (unauthenticated) GET call.
     *
     * @param endpoint The relative API endpoint path.
     * @return A {@link JSONObject} containing the response data.
     * @throws InstagramException If the API returns an error.
     */
    public static JSONObject publicCall(String endpoint) throws InstagramException {
        return callAPI(new Request.Builder()
                .url(Constants.BASE_URL + endpoint)
                .headers(Headers.of(Constants.BASE_HEADERS))
                .header("user-agent", Constants.MOBILE_USER_AGENT.replace(" Android",""))
                .get()
                .build());
    }

    /**
     * Performs an authenticated GET call with optional query parameters.
     *
     * @param endpoint The relative API endpoint path.
     * @param auth     The authentication token.
     * @param params   A map of query parameters.
     * @return A {@link JSONObject} containing the response data.
     * @throws InstagramException If the API returns an error.
     */
    public static JSONObject get(String endpoint, String auth, @Nullable Map<String,Object> params) throws InstagramException {
        return callAPI(new Request.Builder()
                .url(Constants.BASE_URL + endpoint + (params == null ? "" : "?" + map2query(params)))
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("authorization", auth)
                .get().build());
    }

    /**
     * Performs an authenticated POST call with a form body.
     *
     * @param endpoint The relative API endpoint path.
     * @param auth     The authentication token.
     * @param body     A map of form body parameters.
     * @return A {@link JSONObject} containing the response data.
     * @throws InstagramException If the API returns an error.
     */
    public static JSONObject post(@NotNull String endpoint,@NotNull String auth, Map<String, String> body) throws InstagramException {
        var req = new Request.Builder()
                .url(Constants.BASE_URL + endpoint)
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("authorization", auth);


        if (body == null) {
            req.method("POST", RequestBody.create(new byte[0]));
        } else {
            var form = new FormBody.Builder();
            for (Map.Entry<String, String> entry : body.entrySet()) {
                form.addEncoded(entry.getKey(), entry.getValue());
            }
            req.method("POST", form.build());
        }
        return callAPI(req.build());
    }

    /**
     * Injects the Instagram App ID (device ID) into the request headers.
     *
     * @param request The original request.
     * @return A new request with the header added.
     */
    public static Request injectAppId(Request request) {
        return request.newBuilder().addHeader("x-ig-app-id", getDeviceId()).build();
    }

    /**
     * Uploads a picture to Instagram's mobile upload servers.
     *
     * @param in      The input stream of the image data.
     * @param session The authentication token.
     * @return The unique upload ID assigned to the image.
     * @throws IOException        If a network error occurs.
     * @throws InstagramException If the API returns an error.
     */
    public static String uploadPicture(@NotNull InputStream in, @NotNull String session) throws IOException, InstagramException {
        var uploadId = Long.toString(Math.abs(new Random().nextLong()), 36);
        byte[] bytes = in.readAllBytes();

        var uploadReq = new Request.Builder()
                .url("https://i.instagram.com/rupload_igphoto/" + "JxInsta_upload_" + uploadId)
                .post(RequestBody.create(bytes))
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("x-instagram-rupload-params", "{\"media_type\":1,\"upload_id\":\"" + uploadId + "\"}")
                .addHeader("x-entity-length", String.valueOf(bytes.length))
                .addHeader("x-entity-name", "JxInsta_upload_" + uploadId)
                .addHeader("x-entity-type", "image/jpeg")
                .addHeader("offset", String.valueOf(0))
                .header("content-type","image/jpeg");

        var res = client.newCall(uploadReq.build()).execute();
        if (res.isSuccessful()) {
            return uploadId;
        } else {
            throw InstagramException.wrap(new JSONObject(res.body().string()),res.code());
        }
    }

    /**
     * Converts a map of parameters into a URL query string.
     *
     * @param map The map of parameters.
     * @return The formatted query string.
     */
    public static String map2query(Map<String, Object> map) {
        return map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
    }

    /**
     * Converts an Instagram shortcode to a numeric media ID.
     *
     * @param shortCode The shortcode string.
     * @return The numeric media ID.
     */
    public static long shortCode2id(@NotNull String shortCode) {
        int base = ENCODING_CHARS.length();
        int strlen = shortCode.length();
        long num = 0;
        for (int i = 0; i < strlen; i++) {
            char charAtI = shortCode.charAt(i);
            int power = strlen - (i + 1);
            num += (long) (ENCODING_CHARS.indexOf(charAtI) * Math.pow(base, power));
        }
        return num;
    }

    /**
     * Converts a numeric media ID to an Instagram shortcode.
     *
     * @param id The numeric media ID.
     * @return The shortcode string.
     */
    public static String id2shortCode(long id) {
        int base = ENCODING_CHARS.length();
        StringBuilder builder = new StringBuilder();
        while (id > 0) {
            builder.append(ENCODING_CHARS.charAt((int) (id % base)));
            id /= base;
        }
        return builder.reverse().toString();
    }

    /**
     * Generates a signed body for mobile API requests.
     *
     * @param body The body parameters.
     * @return A map containing the "signed_body" parameter.
     */
    public static Map<String,String> genSignedBody(@NotNull Map<String, Object> body){
        var value = "SIGNATURE." + new JSONObject(body);
        return Map.of("signed_body",value);
    }

    /**
     * Generates a random client context UUID for identifying unique requests.
     *
     * @return A random UUID string.
     */
    public static String genClientContext() {
        return java.util.UUID.randomUUID().toString();
    }
}
