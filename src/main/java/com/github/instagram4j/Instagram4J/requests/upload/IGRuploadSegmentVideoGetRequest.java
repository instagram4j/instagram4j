package com.github.instagram4j.Instagram4J.requests.upload;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;

@RequiredArgsConstructor
public class IGRuploadSegmentVideoGetRequest extends IGGetRequest<IGResponse> {
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
        req.addHeader("Stream-Id", stream_id);
        req.addHeader("Segment-Type", "2");
        req.addHeader("Segment-Start-Offset", segment_offset);
        
        return req;
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
