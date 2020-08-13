package com.github.instagram4j.instagram4j.requests;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.IGConstants;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import kotlin.Pair;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public abstract class IGRequest<T extends IGResponse> {

    public abstract String path();

    public abstract Request formRequest(IGClient client);
    
    public abstract Class<T> getResponseType();
    
    public String apiPath() {
        return IGConstants.API_V1;
    }
    
    public String baseApiUrl() {
        return IGConstants.BASE_API_URL;
    }
    
    public String getQueryString(IGClient client) {
        return "";
    }
    
    public HttpUrl formUrl(IGClient client) {
        return HttpUrl.parse(baseApiUrl() + apiPath() + path() + getQueryString(client));
    }
    
    public CompletableFuture<T> execute(IGClient client) {
        return client.sendRequest(this);
    }

    @SneakyThrows
    protected String mapQueryString(Object... strings) {
        StringBuilder builder = new StringBuilder("?");

        for (int i = 0; i < strings.length; i += 2) {
            if (i + 1 < strings.length && strings[i] != null && strings[i + 1] != null && !strings[i].toString().isEmpty()
                    && !strings[i + 1].toString().isEmpty()) {
                builder.append(URLEncoder.encode(strings[i].toString(), "utf-8")).append("=")
                        .append(URLEncoder.encode(strings[i + 1].toString(), "utf-8")).append("&");
            }
        }

        return builder.substring(0, builder.length() - 1);
    }
    
    @SneakyThrows(IOException.class)
    public T parseResponse(Pair<Response, String> response) {
        T igResponse = parseResponse(response.getSecond());
        igResponse.setStatusCode(response.getFirst().code());
        if (igResponse.getStatus().equals("fail")) {
            throw new IGResponseException(igResponse);
        }
        
        return igResponse;
    }

    public T parseResponse(String json) throws IOException {
        return parseResponse(json, getResponseType());
    }

    public <U> U parseResponse(String json, Class<U> type) throws IOException {
        log.debug("{} parsing response : {}", apiPath() + path(), json);
        U response = IGUtils.jsonToObject(json, type);
        
        return response;
    }

    protected Request.Builder applyHeaders(IGClient client, Request.Builder req) {
        req.addHeader("Connection", "close");
        req.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        req.addHeader("Accept-Language", "en-US");
        req.addHeader("X-IG-Capabilities", client.getDevice().getCapabilities());
        req.addHeader("X-IG-App-ID", IGConstants.APP_ID);
        req.addHeader("User-Agent", client.getDevice().getUserAgent());
        req.addHeader("X-IG-Connection-Type", "WIFI");
        req.addHeader("X-Ads-Opt-Out", "0");
        req.addHeader("X-CM-Bandwidth-KBPS", "-1.000");
        req.addHeader("X-CM-Latency", "-1.000");
        req.addHeader("X-IG-App-Locale", "en_US");
        req.addHeader("X-IG-Device-Locale", "en_US");
        req.addHeader("X-Pigeon-Session-Id", IGUtils.randomUuid());
        req.addHeader("X-Pigeon-Rawclienttime", System.currentTimeMillis() + "");
        req.addHeader("X-IG-Connection-Speed", ThreadLocalRandom.current().nextInt(2000, 4000) + "kbps");
        req.addHeader("X-IG-Bandwidth-Speed-KBPS", "-1.000");
        req.addHeader("X-IG-Bandwidth-TotalBytes-B", "0");
        req.addHeader("X-IG-Bandwidth-TotalTime-MS", "0");
        req.addHeader("X-IG-Extended-CDN-Thumbnail-Cache-Busting-Value", "1000");
        req.addHeader("X-IG-Device-ID", client.getGuid());
        req.addHeader("X-IG-Android-ID", client.getDeviceId());
        req.addHeader("X-FB-HTTP-engine", "Liger");
        Optional.ofNullable(client.getAuthorization()).ifPresent(s -> req.addHeader("Authorization", s));

        return req;
    }

}
