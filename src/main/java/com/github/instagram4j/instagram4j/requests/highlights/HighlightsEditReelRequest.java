package com.github.instagram4j.instagram4j.requests.highlights;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class HighlightsEditReelRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String _highlight_id, _title, _cover_media_id;
    private String[] _add_media, _remove_media;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new HighlightsEditReelPayload();
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
    private class HighlightsEditReelPayload extends IGPayload {
        private String title = _title;
        private String cover = IGUtils.objectToJson(new Object() {
            @Getter
            private String media_id = _cover_media_id;
        });
        private String added_media_ids = IGUtils.objectToJson(_add_media);
        private String removed_media_ids = IGUtils.objectToJson(_remove_media);
    }

}
