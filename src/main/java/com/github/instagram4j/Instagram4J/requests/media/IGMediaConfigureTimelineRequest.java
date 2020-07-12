package com.github.instagram4j.Instagram4J.requests.media;

import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.models.location.IGLocation;
import com.github.instagram4j.Instagram4J.models.media.IGUserTag;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaResponse.IGMediaConfigureTimelineResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class IGMediaConfigureTimelineRequest extends IGPostRequest<IGMediaConfigureTimelineResponse> {
    @NonNull
    private IGMediaConfigurePayload payload;

    @Override
    protected IGPayload getPayload() {
        return payload;
    }

    @Override
    public String path() {
        return "media/configure/";
    }

    @Override
    public Class<IGMediaConfigureTimelineResponse> getResponseType() {
        return IGMediaConfigureTimelineResponse.class;
    }

    @Data
    @Accessors(fluent = true)
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    @JsonInclude(Include.NON_NULL)
    public static class IGMediaConfigurePayload extends IGPayload {
        @NonNull
        private final String upload_id;
        @NonNull
        private final String caption;
        private String disable_comments;
        private String location;
        private String usertags;
        
        public IGMediaConfigurePayload location(IGLocation loc) {
            IGLocation payloadLoc = new IGLocation();
            
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
        
        public IGMediaConfigurePayload usertags(IGUserTag... tags) {
            this.usertags = IGUtils.objectToJson(Collections.singletonMap("in", tags));
            
            return this;
        }
        
        public String usertags() {
            return this.usertags;
        }
        
    }

}