package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGLiveAddPostLiveToIgtvRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String _broadcast_id, _title, _description;
    @NonNull
    private Long _cover_upload_id;
    private boolean share_preview;

    @Override
    protected IGPayload getPayload() {
        return new IGLiveAddPostLiveToIgtvPayload();
    }

    @Override
    public String path() {
        return "live/add_post_live_to_igtv/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
    @Data
    public class IGLiveAddPostLiveToIgtvPayload extends IGPayload {
        private String broadcast_id = _broadcast_id;
        private String title = _title;
        private String description = _description;
        private String cover_upload_id = _cover_upload_id.toString();
        private boolean igtv_share_preview_to_feed = share_preview;
    }
}
