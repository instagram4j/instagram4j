package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaListReelMediaViewerResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGMediaListReelMediaViewerRequest extends IGGetRequest<IGMediaListReelMediaViewerResponse> {
    @NonNull
    private String reel_id;
    private String _max_id;

    @Override
    public String path() {
        return "media/" + reel_id + "/list_reel_media_viewer/";
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", _max_id);
    }

    @Override
    public Class<IGMediaListReelMediaViewerResponse> getResponseType() {
        return IGMediaListReelMediaViewerResponse.class;
    }

}
