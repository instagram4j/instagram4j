package com.jxinsta.web;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.org.json.JSONException;
import android.org.json.JSONObject;

/**
 * Utility class containing helper methods for network requests, encoding, and common Instagram API operations.
 */
public class Utils {
    private static int callCount = 0;
    private static final String ENCODING_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    /**
     * Shared OkHttpClient instance for all network calls.
     */
    public static OkHttpClient client = new OkHttpClient().newBuilder().build();

    /**
     * Executes an HTTP request and parses the response into a JSONObject.
     * Handles CSRF token refresh and common Instagram API error wrapping.
     *
     * @param request The OkHttp Request to execute.
     * @return A {@link JSONObject} containing the response data.
     * @throws InstagramException If the API returns an error or parsing fails.
     */
    public static JSONObject callAPI(Request request) throws InstagramException {
        try (Response res = client.newCall(request).execute()) {
            var body = res.body().string();
            JSONObject json;
            try {
                json = new JSONObject(body);
            } catch (JSONException e) {
                throw new InstagramException("Failed to parse response: " + body, InstagramException.Reasons.PARSING_FAILED);
            }

            if (res.isSuccessful()) {
                if (json.has("errors") && json.optJSONObject("data") == null) {
                    throw new InstagramException(json.getJSONArray("errors").getJSONObject(0).toString(), InstagramException.Reasons.UNKNOWN);
                } else {
                    return json.optJSONObject("data", json);
                }
            } else {
                if (res.code() == 400 && json.optString("message").startsWith("CSRF token missing or incorrect")) {
                    var cookies = res.headers("set-cookie");
                    if (!cookies.isEmpty()) {
                        var newCrsf = cookies.get(0).split(";")[0].split("=")[1];
                        var req = request.newBuilder()
                                .removeHeader("x-csrftoken")
                                .addHeader("x-csrftoken", newCrsf)
                                .build();
                        callCount++;
                        if (callCount >= 3) {
                            callCount = 0;
                            throw new InstagramException("CSRF authentication failing multiple times.", InstagramException.Reasons.CSRF_AUTHENTICATION_FAILED);
                        } else {
                            return callAPI(req);
                        }
                    }
                }
                throw InstagramException.wrap(json, res.code());
            }
        } catch (IOException e) {
            throw new InstagramException(e.getMessage(), InstagramException.Reasons.IO);
        }
    }


    /**
     * Fetches a fresh CSRF token from the Instagram login page.
     *
     * @return The CSRF token string.
     * @throws IOException If a network error occurs.
     */
    protected static String getCrsf() throws IOException {
        Request request = new Request.Builder()
                .url("https://www.instagram.com/api/v1/web/login_page/")
                .headers(Headers.of(Constants.BASE_HEADERS))
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.headers("Set-cookie").get(0).split(";")[0].split("=")[1];
        }
    }

    /**
     * Performs a GraphQL query using the mobile user agent.
     *
     * @param session   The session ID cookie.
     * @param docID     The GraphQL document ID.
     * @param variables The variables for the GraphQL query.
     * @return A {@link JSONObject} containing the GraphQL response.
     * @throws InstagramException If the API returns an error.
     */
    public static JSONObject postGraphQL(@Nullable String session, @NotNull String docID, @NotNull JSONObject variables) throws InstagramException {
        var body = new FormBody.Builder()
                .add("doc_id", docID)
                .add("variables", variables.toString())
                .build();

        var req = new Request.Builder()
                .post(body)
                .url("https://www.instagram.com/graphql/query")
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("cookie", session == null ? "" : getCookie(session))
                .addHeader("user-agent", Constants.MOBILE_USER_AGENT)
                .build();

        return callAPI(req);
    }

    /**
     * Performs a public (unauthenticated) GraphQL query.
     *
     * @param docID     The GraphQL document ID.
     * @param variables The variables for the GraphQL query.
     * @return A {@link JSONObject} containing the GraphQL response.
     * @throws InstagramException If the API returns an error.
     */
    public static JSONObject publicGraphQL(@NotNull String docID, @NotNull JSONObject variables) throws InstagramException {
        var body = new FormBody.Builder()
                .add("doc_id", docID)
                .add("variables", variables.toString())
                .add("lsd","jxinsta")
                .build();

        var req = new Request.Builder()
                .post(body)
                .url("https://www.instagram.com/api/graphql")
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("user-agent", Constants.MOBILE_USER_AGENT)
                .addHeader("x-fb-lsd", "jxinsta")
                .build();

        return callAPI(req);
    }

    /**
     * Performs a GraphQL query using the web user agent.
     *
     * @param session   The session ID cookie.
     * @param crsf      The CSRF token.
     * @param docID     The GraphQL document ID.
     * @param variables The variables for the GraphQL query.
     * @return A {@link JSONObject} containing the GraphQL response.
     * @throws InstagramException If the API returns an error.
     */
    public static JSONObject postGraphQLWeb(@NotNull String session,@NotNull String crsf, @NotNull String docID, @NotNull JSONObject variables) throws InstagramException {
        var cookie = getCookie(session);
        var body = new FormBody.Builder()
                .add("doc_id", docID)
                .add("variables", variables.toString())
                .build();

        var req = new Request.Builder()
                .post(body)
                .url("https://www.instagram.com/graphql/query")
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("cookie", cookie)
                .addHeader("user-agent", Constants.WEB_USER_AGENT)
                .addHeader("x-csrftoken", crsf)
                .build();

        return callAPI(req);
    }

    @NotNull
    private static String getCookie(@NotNull String session) {
        return "sessionid=" + session + ";ds_user_id=" + session.split("%")[0];
    }

    /**
     * Performs a public (unauthenticated) GET call to a specific endpoint.
     *
     * @param endpoint The relative API endpoint path.
     * @return A {@link JSONObject} containing the response data.
     * @throws InstagramException If the API returns an error.
     */
    public static JSONObject publicCall(String endpoint) throws InstagramException {
        return callAPI(new Request.Builder()
                .url(Constants.BASE_URL + endpoint)
                .headers(Headers.of(Constants.BASE_HEADERS))
                .header("user-agent", Constants.MOBILE_USER_AGENT)
                .get()
                .build());
    }

    /**
     * Performs an authenticated GET call to a specific endpoint.
     *
     * @param endpoint The relative API endpoint path.
     * @param session  The session ID cookie.
     * @return A {@link JSONObject} containing the response data.
     * @throws InstagramException If the API returns an error.
     */
    public static JSONObject getCall(String endpoint, String session) throws InstagramException {
        var cookie = getCookie(session);
        return callAPI(new Request.Builder()
                .url(Constants.BASE_URL + endpoint)
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("cookie", cookie)
                .header("user-agent", Constants.MOBILE_USER_AGENT)
                .get().build());
    }

    /**
     * Performs an authenticated POST call to a specific endpoint.
     *
     * @param session  The session ID cookie.
     * @param crsf     The CSRF token.
     * @param endpoint The relative API endpoint path.
     * @param body     A map of form body parameters. Pass {@code null} for an empty body.
     * @throws InstagramException If the API returns an error.
     */
    public static void postCall(@NotNull String session, @NotNull String crsf, String endpoint, Map<String, Object> body) throws InstagramException {
        var req = new Request.Builder()
                .url(Constants.BASE_URL + endpoint)
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("cookie", "sessionid=" + session)
                .addHeader("user-agent", Constants.WEB_USER_AGENT)
                .addHeader("x-csrftoken", crsf);

        if (body == null) {
            req.method("POST", RequestBody.create(new byte[0]));
        } else {
            var form = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : body.entrySet()) {
                form.add(entry.getKey(), (String) entry.getValue());
            }
            req.method("POST", form.build());
        }
        callAPI(req.build());
    }

    /**
     * Performs a GraphQL query using GET with query parameters.
     *
     * @param queryId The GraphQL query ID.
     * @param params  A map of parameters to be appended to the URL.
     * @param session The session ID cookie (optional).
     * @return A {@link JSONObject} containing the response data.
     * @throws InstagramException If the API returns an error.
     */
    public static JSONObject graphql(@NotNull String queryId, @NotNull Map<String, Object> params, @Nullable String session) throws InstagramException {
        var furl = "https://www.instagram.com/graphql/query/?query_id=" + queryId + "&" + params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
        return callAPI(new Request.Builder()
                .url(furl)
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("cookie", session == null ? "" : getCookie(session))
                .header("user-agent", Constants.MOBILE_USER_AGENT)
                .get().build());
    }

    /**
     * Uploads a picture to Instagram's upload servers.
     *
     * @param in      The input stream of the image data.
     * @param session The session ID cookie.
     * @return The unique upload ID assigned to the image.
     * @throws IOException If a network error or read error occurs.
     */
    public static String uploadPicture(@NotNull InputStream in, @NotNull String session) throws IOException {
        var uploadId = Long.toString(Math.abs(new Random().nextLong()), 36);
        byte[] bytes = in.readAllBytes();

        var uploadReq = new Request.Builder()
                .url("https://i.instagram.com/rupload_igphoto/" + "JxInsta_upload_" + uploadId)
                .post(RequestBody.create(bytes))
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("user-agent", Constants.MOBILE_USER_AGENT)
                .addHeader("x-instagram-rupload-params", "{\"media_type\":1,\"upload_id\":\"" + uploadId + "\"}")
                .addHeader("x-entity-length", String.valueOf(bytes.length))
                .addHeader("x-entity-name", "JxInsta_upload_" + uploadId)
                .addHeader("x-entity-type", "image/jpeg")
                .addHeader("offset", String.valueOf(0))
                .removeHeader("content-type")
                .addHeader("cookie", "sessionid=" + session)
                .addHeader("content-type", "application/octet-stream");

        try (Response res = client.newCall(uploadReq.build()).execute()) {
            if (res.isSuccessful()) {
                return uploadId;
            } else {
                throw new IOException("Failed to upload picture: " + res.body().string());
            }
        }
    }

    /**
     * Converts an Instagram shortcode (e.g., in /p/CODE/) to a numeric media ID.
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
}
