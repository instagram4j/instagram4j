package com.github.instagram4j.instagram4j.requests.media;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.models.location.Location;
import com.github.instagram4j.instagram4j.models.media.UserTags;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureToClipsResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import lombok.*;
import lombok.experimental.Accessors;
import okhttp3.Request;

import java.util.Collections;

@RequiredArgsConstructor
public class MediaConfigureToClipsRequest extends IGPostRequest<MediaConfigureToClipsResponse> {
    @NonNull
    private MediaConfigureToClipsPayload payload;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return payload;
    }

    @Override
    public String path() {
        return "media/configure_to_clips/";
    }

    @Override
    public Class<MediaConfigureToClipsResponse> getResponseType() {
        return MediaConfigureToClipsResponse.class;
    }

    @Override
    protected Request.Builder applyHeaders(IGClient client, Request.Builder req) {
        Request.Builder builder = super.applyHeaders(client, req);
        return builder;
    }

    @Data
    @Accessors(fluent = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonInclude(Include.NON_NULL)
    @Setter
    public static class MediaConfigureToClipsPayload extends IGPayload {
        private String upload_id;
        private String caption = "";
        private String source_type = "4";
        private String feed_show = "1";
        private String length;
        private String retryContext =
                "{\"num_step_auto_retry\":0,\"num_reupload\":0,\"num_step_manual_retry\":0}";
        private String disable_comments;
        private String location;
        private String usertags;


        public MediaConfigureToClipsPayload location(Location loc) {
            Location payloadLoc = new Location();

            payloadLoc.setExternal_id(loc.getExternal_id());
            payloadLoc.setName(loc.getName());
            payloadLoc.setAddress(loc.getAddress());
            payloadLoc.setLat(loc.getLat());
            payloadLoc.setLng(loc.getLng());
            payloadLoc.setExternal_source(loc.getExternal_source());
            payloadLoc.put(payloadLoc.getExternal_source() + "_id", payloadLoc.getExternal_id());
            this.location = IGUtils.objectToJson(payloadLoc);
            this.put("geotag_enabled", "1");
            this.put("posting_latitude", payloadLoc.getLat().toString());
            this.put("posting_longitude", payloadLoc.getLng().toString());
            this.put("media_latitude", payloadLoc.getLat().toString());
            this.put("media_longitude", payloadLoc.getLng().toString());

            return this;
        }

        public MediaConfigureToClipsPayload usertags(UserTags.UserTagPayload... tags) {
            this.usertags = IGUtils.objectToJson(Collections.singletonMap("in", tags));

            return this;
        }

        public String usertags() {
            return this.usertags;
        }
    }

}
