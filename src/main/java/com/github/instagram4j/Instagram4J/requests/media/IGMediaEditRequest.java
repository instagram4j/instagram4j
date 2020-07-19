package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGMediaEditRequest extends IGPostRequest<IGMediaResponse> {
    @NonNull
    private String id, _caption;
    private boolean _igtv_feed_preview;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGMediaEditPayload();
    }

    @Override
    public String path() {
        return "media/" + id + "/edit_media/";
    }

    @Override
    public Class<IGMediaResponse> getResponseType() {
        return IGMediaResponse.class;
    }
    
    @Data
    public class IGMediaEditPayload extends IGPayload {
        private String media_id = id;
        private String caption = _caption;
        private boolean igtv_feed_preview= _igtv_feed_preview;
    }

}
