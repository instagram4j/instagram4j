package com.github.instagram4j.Instagram4J.requests.direct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGDirectThreadsBroadcastRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private final IGBroadcastPayload payload;

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

        public String getItemType() {
            return "text";
        }
    }

    @Data
    public static class IGBroadcastMediaSharePayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String media_id;

        public String getItemType() {
            return "media_share";
        }
    }

    @Data
    public static class IGBroadcastReelSharePayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String media_id;
        @NonNull
        private String text;

        @Override
        public String getItemType() {
            return "reel_share";
        }
    }

    @Data
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class IGBroadcastStorySharePayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String story_media_id;
        private String text = "";

        @Override
        public String getItemType() {
            return "story_share";
        }
    }

    @Data
    public static class IGBroadcastShareVoicePayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String upload_id;
        private int waveform_sampling_frequency_hz = 10;
        private String waveform = IGUtils.objectToJson(new double[] { 0.146, 0.5, 0.854, 1, 0.854, 0.5, 0.146, 0, 0.146,
                0.5, 0.854, 1, 0.854, 0.5, 0.146, 0, 0.146, 0.5, 0.854, 1 });

        @Override
        public String getItemType() {
            return "share_voice";
        }
    }

    @Data
    public static class IGBroadcastProfilePayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String profile_user_id;

        public String getItemType() {
            return "profile";
        }
    }

    @Data
    public static class IGBroadcastConfigurePhotoPayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String upload_id;

        public String getItemType() {
            return "configure_photo";
        }
    }

    @Data
    public static class IGBroadcastConfigureVideoPayload extends IGBroadcastPayload {
        @NonNull
        private String thread_id;
        @NonNull
        private String upload_id;

        public String getItemType() {
            return "configure_video";
        }
    }

    @Data
    public static abstract class IGBroadcastPayload extends IGPayload {
        private String action = "send_item";

        @JsonIgnore
        public abstract String getItemType();

        public abstract String getThread_id();
    }

}
