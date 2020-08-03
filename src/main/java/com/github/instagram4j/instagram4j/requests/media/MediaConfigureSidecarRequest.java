package com.github.instagram4j.instagram4j.requests.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGBaseModel;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.models.location.Location;
import com.github.instagram4j.instagram4j.models.media.UserTags.UserTagPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureSidecarResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class MediaConfigureSidecarRequest extends IGPostRequest<MediaConfigureSidecarResponse> {
    @NonNull
    private final MediaConfigureSidecarPayload payload;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return payload;
    }

    @Override
    public String path() {
        return "media/configure_sidecar/";
    }

    @Override
    public Class<MediaConfigureSidecarResponse> getResponseType() {
        return MediaConfigureSidecarResponse.class;
    }
    
    @Data
    @Accessors(fluent = true)
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    @JsonInclude(Include.NON_NULL)
    public static class MediaConfigureSidecarPayload extends IGPayload {
        private List<SidecarChildrenMetadata> children_metadata = new ArrayList<>();
        private String caption = "";
        private String client_sidecar_id = String.valueOf(System.currentTimeMillis());
        private String location;
        private String disable_comments;
        
        public MediaConfigureSidecarPayload location(Location loc) {
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
    }

    @Data
    @Accessors(fluent = true)
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    @JsonInclude(Include.NON_NULL)
    public static class SidecarChildrenMetadata extends IGBaseModel {
        @NonNull
        private final String upload_id;
        private String usertags;
        
        public SidecarChildrenMetadata usertags(UserTagPayload... tags) {
            this.usertags = IGUtils.objectToJson(Collections.singletonMap("in", tags));
            
            return this;
        }
        
        public String usertag() {
            return usertags;
        }
    }
}
