package com.github.instagram4j.Instagram4J.requests.highlights;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGHighlightsEditReelRequest extends IGPostRequest<IGResponse> {
    @NonNull 
    private String _highlight_id, _title, _cover_media_id;
    private String[] _add_media, _remove_media;
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGHighlightsEditReelPayload();
    }

    @Override
    public String path() {
        return "highlights/" + _highlight_id + "/edit_reel/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
    @Data
    private class IGHighlightsEditReelPayload extends IGPayload {
        private String title = _title;
        private String cover = IGUtils.objectToJson(new Object() {
            @Getter
            private String media_id = _cover_media_id;
        });
        private String added_media_ids = IGUtils.objectToJson(_add_media);
        private String removed_media_ids = IGUtils.objectToJson(_remove_media);
    }

}
