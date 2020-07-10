package com.github.instagram4j.Instagram4J.requests.upload;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.media.IGUploadParameters;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;

@RequiredArgsConstructor
public class IGRuploadSegmentVideoGetRequest extends IGGetRequest<IGResponse> {
    @NonNull
    private IGUploadParameters param;
    @NonNull
    private String stream_id, transfer_id, segment_offset;

    @Override
    public String apiPath() {
        return "";
    }

    @Override
    public String path() {
        return "rupload_igvideo/" + transfer_id;
    }

    @Override
    public Request.Builder applyHeaders(IGClient client, Request.Builder req) {
        super.applyHeaders(client, req);
        req.addHeader("X-Instagram-Rupload-Params", IGUtils.objectToJson(param));
        req.addHeader("Stream-Id", stream_id);
        req.addHeader("Segment-Type", "2");
        req.addHeader("Segment-Start-Offset", segment_offset);
        req.addHeader("X_FB_WATERFALL_ID", IGUtils.randomUuid());
        return req;
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
