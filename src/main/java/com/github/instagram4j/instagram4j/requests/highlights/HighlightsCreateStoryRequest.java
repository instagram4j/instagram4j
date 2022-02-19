package com.github.instagram4j.instagram4j.requests.highlights;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.highlights.HighlightsCreateStoryResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class HighlightsCreateStoryRequest extends IGPostRequest<HighlightsCreateStoryResponse> {
    @NonNull
    private String _title;
    @NonNull
    private String[] _media_ids;
    private String _cover_media_id;

    public HighlightsCreateStoryRequest(String title, String... media_ids) {
        this._title = title;
        this._media_ids = media_ids;
        this._cover_media_id = this._media_ids[0];
    }

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new HighlightsCreateStoryPayload();
    }

    @Override
    public String path() {
        return "highlights/create_reel/";
    }

    @Override
    public Class<HighlightsCreateStoryResponse> getResponseType() {
        return HighlightsCreateStoryResponse.class;
    }

    @Data
    public class HighlightsCreateStoryPayload extends IGPayload {
        private String creation_id = String.valueOf(System.currentTimeMillis());
        private String title = _title;
        private String cover = IGUtils.objectToJson(new Object() {
            @Getter
            private String media_id = _cover_media_id;
        });
        private String media_ids = IGUtils.objectToJson(_media_ids);
    }

}
