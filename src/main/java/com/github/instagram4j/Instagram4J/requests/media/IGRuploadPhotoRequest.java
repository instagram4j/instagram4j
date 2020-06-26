package com.github.instagram4j.Instagram4J.requests.media;

import java.util.concurrent.ThreadLocalRandom;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.models.IGUploadParameters;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGRuploadPhotoResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGRuploadPhotoRequest extends IGPostRequest<IGRuploadPhotoResponse> {
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
    public IGPayload getPayload() {
        return null;
    }

    @Override
    protected Request.Builder applyHeaders(Request.Builder req) {
        super.applyHeaders(req);
        req.addHeader("X-Instagram-Rupload-Params", IGUploadParameters.forPhoto(uploadId, mediaType, isSidecar));
        req.addHeader("X_FB_WATERFALL_ID", IGUtils.randomUuid());
        req.addHeader("Accept-Encoding", "gzip");
        req.addHeader("X-Entity-Name", name);
        req.addHeader("X-Entity-Type", "image/jpeg");
        req.addHeader("X-Entity-Length", String.valueOf(imgData.length));
        req.addHeader("Offset", "0");

        return req;
    }

    @Override
    protected RequestBody getRequestBody() {
        return RequestBody.create(imgData, MediaType.get("application/octet-stream"));
    }

    @Override
    public String path() {
        return "/rupload_igphoto/" + name;
    }

    @Override
    public Class<IGRuploadPhotoResponse> getResponseType() {
        return IGRuploadPhotoResponse.class;
    }

}
