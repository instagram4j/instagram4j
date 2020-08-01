package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.requests.PaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.media.MediaListReelMediaViewerResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
public class MediaListReelMediaViewerRequest extends IGGetRequest<MediaListReelMediaViewerResponse> implements PaginatedRequest<MediaListReelMediaViewerResponse> {
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
