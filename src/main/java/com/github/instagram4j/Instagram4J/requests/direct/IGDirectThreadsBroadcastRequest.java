package com.github.instagram4j.Instagram4J.requests.direct;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.IGClient;
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
    protected IGPayload getPayload(IGClient client) {
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
    @JsonInclude(Include.NON_NULL)
    public static abstract class IGBroadcastPayload extends IGPayload {
        private String action = "send_item";

        @JsonIgnore
        public abstract String getItemType();

        public abstract String getThread_ids();
        
        public abstract String getRecipient_users();
    }

    @Data
    public static class IGBroadcastTextPayload extends IGBroadcastPayload {
        private final String thread_ids;
        private final String recipient_users;
        private final String text;
        
        public IGBroadcastTextPayload(String text, long... pks) {
            this.thread_ids = null;
            this.recipient_users = IGUtils.objectToJson(Arrays.asList(pks));
            this.text = text;
        }
        
        public IGBroadcastTextPayload(String text, String... thread_ids) {
            this.thread_ids = IGUtils.objectToJson(thread_ids);
            this.recipient_users = null;
            this.text = text;
        }

        public String getItemType() {
            return "text";
        }
    }

    @Data
    public static class IGBroadcastMediaSharePayload extends IGBroadcastPayload {
        private final String thread_ids;
        private final String recipient_users;
        private final String text;
        private final String media_id;
        
        public IGBroadcastMediaSharePayload(String media_id, String text, long... pks) {
            this.thread_ids = null;
            this.recipient_users = IGUtils.objectToJson(Arrays.asList(pks));
            this.media_id = media_id;
            this.text = text;
        }
        
        public IGBroadcastMediaSharePayload(String media_id, String text, String... thread_ids) {
            this.thread_ids = IGUtils.objectToJson(thread_ids);
            this.recipient_users = null;
            this.media_id = media_id;
            this.text = text;
        }
        
        @Override
        public String getItemType() {
            return "media_share";
        }
    }

    @Data
    public static class IGBroadcastReelSharePayload extends IGBroadcastPayload {
        private final String thread_ids;
        private final String recipient_users;
        private final String text;
        private final String media_id;
        
        public IGBroadcastReelSharePayload(String media_id, String text, long... pks) {
            this.thread_ids = null;
            this.recipient_users = IGUtils.objectToJson(Arrays.asList(pks));
            this.media_id = media_id;
            this.text = text;
        }
        
        public IGBroadcastReelSharePayload(String media_id, String text, String... thread_ids) {
            this.thread_ids = IGUtils.objectToJson(thread_ids);
            this.recipient_users = null;
            this.media_id = media_id;
            this.text = text;
        }

        @Override
        public String getItemType() {
            return "reel_share";
        }
    }

    @Data
    public static class IGBroadcastStorySharePayload extends IGBroadcastPayload {
        private final String thread_ids;
        private final String recipient_users;
        private final String story_media_id;
        private final String text;
        
        public IGBroadcastStorySharePayload(String media_id, String text, long... pks) {
            this.thread_ids = null;
            this.recipient_users = IGUtils.objectToJson(Arrays.asList(pks));
            this.story_media_id = media_id;
            this.text = text;
        }
        
        public IGBroadcastStorySharePayload(String media_id, String text, String... thread_ids) {
            this.thread_ids = IGUtils.objectToJson(thread_ids);
            this.recipient_users = null;
            this.story_media_id = media_id;
            this.text = text;
        }
        
        @Override
        public String getItemType() {
            return "story_share";
        }
    }

    @Data
    @AllArgsConstructor
    public static class IGBroadcastShareVoicePayload extends IGBroadcastPayload {
        private final String thread_ids;
        private final String recipient_users;
        private final String upload_id;
        private String waveform = IGUtils.objectToJson(new double[] { 0.146, 0.5, 0.854, 1, 0.854, 0.5, 0.146, 0, 0.146,
                0.5, 0.854, 1, 0.854, 0.5, 0.146, 0, 0.146, 0.5, 0.854, 1 });
        private int waveform_sampling_frequency_hz = 10;
        
        public IGBroadcastShareVoicePayload(String upload_id, long... pks) {
            this.thread_ids = null;
            this.recipient_users = IGUtils.objectToJson(Arrays.asList(pks));
            this.upload_id = upload_id;
        }
        
        public IGBroadcastShareVoicePayload(String upload_id, String... upload_ids) {
            this.thread_ids = IGUtils.objectToJson(upload_ids);
            this.recipient_users = null;
            this.upload_id = upload_id;
        }
        
        @Override
        public String getItemType() {
            return "share_voice";
        }
    }

    @Data
    public static class IGBroadcastProfilePayload extends IGBroadcastPayload {
        private final String thread_ids;
        private final String recipient_users;
        private final String profile_user_id;
        
        public IGBroadcastProfilePayload(String user_id, long... pks) {
            this.thread_ids = null;
            this.recipient_users = IGUtils.objectToJson(Arrays.asList(pks));
            this.profile_user_id = user_id;
        }
        
        public IGBroadcastProfilePayload(String user_id, String... thread_ids) {
            this.thread_ids = IGUtils.objectToJson(thread_ids);
            this.recipient_users = null;
            this.profile_user_id = user_id;
        }
        
        public String getItemType() {
            return "profile";
        }
    }

    @Data
    public static class IGBroadcastConfigurePhotoPayload extends IGBroadcastPayload {
        private final String thread_ids;
        private final String recipient_users;
        private final String upload_id;
        
        public IGBroadcastConfigurePhotoPayload(String upload_id, long... pks) {
            this.thread_ids = null;
            this.recipient_users = IGUtils.objectToJson(Arrays.asList(pks));
            this.upload_id = upload_id;
        }
        
        public IGBroadcastConfigurePhotoPayload(String upload_id, String... thread_ids) {
            this.thread_ids = IGUtils.objectToJson(thread_ids);
            this.recipient_users = null;
            this.upload_id = upload_id;
        }
        
        public String getItemType() {
            return "configure_photo";
        }
    }

    @Data
    public static class IGBroadcastConfigureVideoPayload extends IGBroadcastPayload {
        private final String thread_ids;
        private final String recipient_users;
        private final String upload_id;
        
        public IGBroadcastConfigureVideoPayload(String upload_id, long... pks) {
            this.thread_ids = null;
            this.recipient_users = IGUtils.objectToJson(Arrays.asList(pks));
            this.upload_id = upload_id;
        }
        
        public IGBroadcastConfigureVideoPayload(String upload_id, String... thread_ids) {
            this.thread_ids = IGUtils.objectToJson(thread_ids);
            this.recipient_users = null;
            this.upload_id = upload_id;
        }
        
        public String getItemType() {
            return "configure_video";
        }
    }
    
    @Data
    public static class IGBroadcastLinkPayload extends IGBroadcastPayload {
        private final String thread_ids;
        private final String recipient_users;
        private final String link_text;
        private final String link_urls;
        
        public IGBroadcastLinkPayload(String text, String[] link_urls, long... pks) {
            this.thread_ids = null;
            this.recipient_users = IGUtils.objectToJson(Arrays.asList(pks));
            this.link_text = text;
            this.link_urls = IGUtils.objectToJson(link_urls);
        }
        
        public IGBroadcastLinkPayload(String text, String[] link_urls, String... thread_ids) {
            this.thread_ids = IGUtils.objectToJson(thread_ids);
            this.recipient_users = null;
            this.link_text = text;
            this.link_urls = IGUtils.objectToJson(link_urls);        
        }
        
        public String getItemType() {
            return "link";
        }
    }

}
