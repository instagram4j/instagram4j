package com.github.instagram4j.instagram4j.requests.media;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;

@RequiredArgsConstructor
@AllArgsConstructor
public class ConfigureToIgtvRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String _title, _caption, _upload_id;
    private boolean share_preview;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new LiveAddPostLiveToIgtvPayload();
    }

    @Override
    public String path() {
        return "media/configure_to_igtv/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

    @Override
    protected Request.Builder applyHeaders(IGClient client, Request.Builder req) {
        Request.Builder builder = super.applyHeaders(client, req);
        builder.addHeader("is_igtv_video", "1");
        builder.addHeader("retry_context", "{\"num_step_auto_retry\":0,\"num_reupload\":0,\"num_step_manual_retry\":0}");
        builder.addHeader("Priority", "u=3");
        return builder;
    }

    @Data
    public class LiveAddPostLiveToIgtvPayload extends IGPayload {
        private String title = _title;
        private String caption = _caption;
        private String upload_id = _upload_id;
        private boolean igtv_share_preview_to_feed = share_preview;
        private String source_type = "4";
        private boolean keep_shoppable_products = false;
        private boolean igtv_ads_toggled_on = false;
        private String igtv_composer_session_id = IGUtils.randomUuid();
    }
}
