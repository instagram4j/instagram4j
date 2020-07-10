package com.github.instagram4j.Instagram4J.requests.media;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.models.media.reel.IGReelMedia;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGMediaSeenRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private List<IGReelMedia> reel_media_ids;

    @Override
    public String apiPath() {
        return "api/v2/";
    }

    @Override
    protected IGPayload getPayload() {
        return new IGMediaSeenPayload(reel_media_ids);
    }

    @Override
    public String path() {
        return "media/seen/";
    }

    @Override
    public String getQueryString() {
        return mapQueryString("reel", "1");
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
    @Data
    public class IGMediaSeenPayload extends IGPayload {
        private Map<String, String[]> reels;
        
        public IGMediaSeenPayload(List<IGReelMedia> reelMedias) {
            this.reels = new HashMap<>();
            reelMedias.forEach(this::map_to_reels);
        }
        
        public void map_to_reels(IGReelMedia media) {
            String key = String.format("%s_%s", media.getId(), media.getUser().getPk());
            String[] value = {String.format("%s_%s", media.getTaken_at(), System.currentTimeMillis() / 1000)};
            
            reels.put(key, value);
        }
    }
    
}
