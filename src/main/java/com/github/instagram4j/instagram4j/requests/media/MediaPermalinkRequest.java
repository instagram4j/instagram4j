package com.github.instagram4j.instagram4j.requests.media;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.media.MediaPermalinkResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MediaPermalinkRequest extends IGGetRequest<MediaPermalinkResponse> {
    @NonNull
    private String media_id;
    
    @Override
    public String path() {
        return "media/" + media_id + "/permalink/";
    }

    @Override
    public Class<MediaPermalinkResponse> getResponseType() {
        return MediaPermalinkResponse.class;
    }

}
