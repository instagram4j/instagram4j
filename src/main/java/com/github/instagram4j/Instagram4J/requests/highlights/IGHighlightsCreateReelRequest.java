package com.github.instagram4j.Instagram4J.requests.highlights;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.highlights.IGHighlightsCreateReelResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class IGHighlightsCreateReelRequest extends IGPostRequest<IGHighlightsCreateReelResponse> {
    @NonNull
    private String _title;
    @NonNull
    private String[] _media_ids;
    private String _cover_media_id;
    
    public IGHighlightsCreateReelRequest(String title, String... media_ids) {
        this._title = title;
        this._media_ids = media_ids;
        this._cover_media_id = this._media_ids[0];
    }
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGHighlightsCreateReelPayload();
    }

    @Override
    public String path() {
        return "highlights/create_reel/";
    }

    @Override
    public Class<IGHighlightsCreateReelResponse> getResponseType() {
        return IGHighlightsCreateReelResponse.class;
    }
    
    @Data
    public class IGHighlightsCreateReelPayload extends IGPayload {
        private String creation_id = String.valueOf(System.currentTimeMillis());
        private String title = _title;
        private String cover = IGUtils.objectToJson(new Object() {
            @Getter
            private String media_id = _cover_media_id;
        });
        private String media_ids = IGUtils.objectToJson(_media_ids);
    }

}
