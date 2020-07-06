package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.live.IGLiveBroadcastGetViewerListResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGLiveBroadcastGetViewerListRequest extends IGGetRequest<IGLiveBroadcastGetViewerListResponse> {
    @NonNull
    private String broadcast_id;

    @Override
    public String path() {
        return "live/" + broadcast_id + "/get_viewer_list/";
    }

    @Override
    public Class<IGLiveBroadcastGetViewerListResponse> getResponseType() {
        return IGLiveBroadcastGetViewerListResponse.class;
    }

}
