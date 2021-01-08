package com.github.instagram4j.instagram4j.requests.media;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.responses.media.MediaListReelMediaViewerResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
public class MediaListReelMediaViewerRequest extends IGGetRequest<MediaListReelMediaViewerResponse>
        implements IGPaginatedRequest {
    @NonNull
    private String reel_id;
    @Setter
    private String max_id;

    @Override
    public String path() {
        return "media/" + reel_id + "/list_reel_media_viewer/";
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id);
    }

    @Override
    public Class<MediaListReelMediaViewerResponse> getResponseType() {
        return MediaListReelMediaViewerResponse.class;
    }

}
