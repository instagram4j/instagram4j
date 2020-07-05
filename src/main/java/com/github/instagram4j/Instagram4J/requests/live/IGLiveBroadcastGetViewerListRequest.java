package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//TODO: Response and Test
public class IGLiveBroadcastGetViewerListRequest extends IGGetRequest<IGResponse> {
    @NonNull
    private String broadcast_id;

    @Override
    public String path() {
        return "live/" + broadcast_id + "/get_viewer_list/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
