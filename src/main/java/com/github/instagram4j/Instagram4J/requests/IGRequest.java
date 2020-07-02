package com.github.instagram4j.Instagram4J.requests;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.IGConstants;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.SneakyThrows;
import okhttp3.Request;

public abstract class IGRequest<T extends IGResponse> {

    public abstract String path();

    public abstract Request formRequest(IGClient client);
    
    public abstract Class<T> getResponseType();

    public String getQueryString() {
        return "";
    }
    
    public T execute(IGClient client) throws IGResponseException {
        return client.sendRequest(this);
    }
    
    public <E> E execute(IGClient client, Class<E> clazz) throws IGResponseException {
        return client.sendRequest(this, clazz);
    }

    @SneakyThrows
    protected String mapQueryString(String... strings) {
        StringBuilder builder = new StringBuilder("?");

        for (int i = 0; i < strings.length; i += 2) {
            if (i + 1 < strings.length && strings[i] != null && strings[i + 1] != null && !strings[i].isEmpty()
                    && !strings[i + 1].isEmpty()) {
                builder.append(URLEncoder.encode(strings[i], "utf-8")).append("=")
                        .append(URLEncoder.encode(strings[i + 1], "utf-8")).append("&");
            }
        }

        return builder.substring(0, builder.length() - 1);
    }

    public String apiPath() {
        return IGConstants.API_V1;
    }

    public T parseResponse(String json) throws JsonProcessingException, IOException {
        return parseResponse(json, getResponseType());
    }

    public <U> U parseResponse(String json, Class<U> type) throws JsonMappingException, JsonProcessingException {
        U response = IGUtils.MAPPER.readValue(json, type);
        // response.setClient(client);

        return response;
    }

    protected Request.Builder applyHeaders(IGClient client, Request.Builder req) {
        req.addHeader("Connection", "close");
        req.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        req.addHeader("Accept-Language", "en-US");
        req.addHeader("X-IG-Capabilities", IGConstants.DEVICE_CAPABILITIES);
        req.addHeader("X-IG-App-ID", IGConstants.APP_ID);
        req.addHeader("User-Agent", client.getUserAgent());
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
        req.addHeader("X-FB-HTTP-engine", "Liger");

        return req;
    }

}
