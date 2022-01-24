package com.github.instagram4j.instagram4j.requests.upload;

import java.util.concurrent.ThreadLocalRandom;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.media.RuploadPhotoResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RuploadPhotoRequest extends IGPostRequest<RuploadPhotoResponse> {
    private final byte[] imgData;
    private final String mediaType;
    private final String uploadId;
    private final String broadcastId;
    private final boolean isSidecar;
    private final String name;
    
    public RuploadPhotoRequest(byte[] imgData, String mediaType) {
        this(imgData, mediaType, String.valueOf(System.currentTimeMillis()), false);
    }
    
    public RuploadPhotoRequest(byte[] imgData, String mediaType, String uploadId, boolean isSidecar) {
        this(imgData, mediaType, uploadId, null, isSidecar);
    }
    
    public RuploadPhotoRequest(byte[] imgData, String mediaType, String uploadId, String broadcastId, boolean isSidecar) {
        this.imgData = imgData;
        this.mediaType = mediaType;
        this.uploadId = uploadId;
        this.broadcastId = broadcastId;
        this.isSidecar = isSidecar;
        this.name = uploadId + "_0_" + ThreadLocalRandom.current().nextLong(1_000_000_000, 9_999_999_999l);
    }

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
                UploadParameters.forPhoto(uploadId, mediaType, isSidecar, broadcastId).toString());
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
