package com.github.instagram4j.instagram4j.requests.upload;

import java.util.concurrent.ThreadLocalRandom;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.media.RuploadPhotoResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

@RequiredArgsConstructor
@AllArgsConstructor
public class RuploadPhotoRequest extends IGPostRequest<RuploadPhotoResponse> {
    @NonNull
    private byte[] imgData;
    @NonNull
    private String mediaType;
    private String uploadId = String.valueOf(System.currentTimeMillis());
    private boolean isSidecar = false;
    private final String name = uploadId + "_0_" + ThreadLocalRandom.current().nextLong(1_000_000_000, 9_999_999_999l);
    
    @Override
    public String apiPath() {
        return "";
    }

    @Override
    public IGPayload getPayload(IGClient client) {
        return null;
    }

    @Override
    protected Request.Builder applyHeaders(IGClient client, Request.Builder req) {
        super.applyHeaders(client, req);
        req.addHeader("X-Instagram-Rupload-Params",
                UploadParameters.forPhoto(uploadId, mediaType, isSidecar).toString());
        req.addHeader("X_FB_WATERFALL_ID", IGUtils.randomUuid());
        req.addHeader("Accept-Encoding", "gzip");
        req.addHeader("X-Entity-Name", name);
        req.addHeader("X-Entity-Type", "image/jpeg");
        req.addHeader("X-Entity-Length", String.valueOf(imgData.length));
        req.addHeader("Offset", "0");

        return req;
    }

    @Override
    protected RequestBody getRequestBody(IGClient client) {
        return RequestBody.create(imgData, MediaType.get("application/octet-stream"));
    }

    @Override
    public String path() {
        return "rupload_igphoto/" + name;
    }

    @Override
    public Class<RuploadPhotoResponse> getResponseType() {
        return RuploadPhotoResponse.class;
    }

}
