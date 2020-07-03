package com.github.instagram4j.Instagram4J.requests.upload;

import java.util.concurrent.ThreadLocalRandom;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.models.IGUploadParameters;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class IGRuploadVideoRequest extends IGPostRequest<IGResponse> {

    private byte[] videoData;
    private IGUploadParameters upload_params;
    private final String name;

    public IGRuploadVideoRequest(byte data[], IGUploadParameters param) {
        this.videoData = data;
        this.upload_params = param;
        name = upload_params.getUpload_id() + "_0_"
                + ThreadLocalRandom.current().nextLong(1_000_000_000, 9_999_999_999l);
    }

    @Override
    protected IGPayload getPayload() {
        return null;
    }

    @Override
    public String apiPath() {
        return "";
    }

    @Override
    public String path() {
        return "rupload_igvideo/" + name;
    }

    @Override
    public Request.Builder applyHeaders(IGClient client, Request.Builder req) {
        super.applyHeaders(client, req);
        req.addHeader("X-Instagram-Rupload-Params", IGUtils.objectToJson(upload_params));
        req.addHeader("X_FB_VIDEO_WATERFALL_ID", IGUtils.randomUuid());
        req.addHeader("X-Entity-Type", "video/mp4");
        req.addHeader("Offset", "0");
        req.addHeader("X-Entity-Name", name);
        req.addHeader("X-Entity-Length", String.valueOf(videoData.length));

        return req;
    }

    @Override
    public RequestBody getRequestBody(IGClient client) {
        return RequestBody.create(videoData, MediaType.get("application/octet-stream"));
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}