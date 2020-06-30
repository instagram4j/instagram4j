package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGDirectThreadsBroadcastRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private IGBroadcastPayload payload;
    
    @Override
    protected IGPayload getPayload() {
        return payload;
    }

    @Override
    public String path() {
        return "direct_v2/threads/broadcast/" + payload.getItemType() + "/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
    @Data
    public static class IGBroadcastTextPayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String text;
        public String getItemType() { return "text"; }
    }
    
    @Data
    public static class IGBroadcastConfigurePhotoPayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String upload_id;
        public String getItemType() { return "configure_photo"; }
    }
    
    @Data
    public static class IGBroadcastConfigureVideoPayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String upload_id;
        public String getItemType() { return "configure_video"; }
    }
    
    @Data
    public static abstract class IGBroadcastPayload extends IGPayload {
        private String action = "send_item";
        public abstract String getItemType();
        public abstract String getThread_id();
    }
    
}
