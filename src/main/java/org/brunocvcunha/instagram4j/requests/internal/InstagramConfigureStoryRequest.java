/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests.internal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureStoryResult;
import org.brunocvcunha.instagram4j.storymetadata.StoryMetadata;
import org.brunocvcunha.inutils4j.MyImageUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * InstagramConfigureStoryPhotoRequest
 * @author Justin Vo
 *
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramConfigureStoryRequest extends InstagramPostRequest<InstagramConfigureStoryResult> {
    @NonNull
    private File mediaFile;
    @NonNull
    private String uploadId;
    
    private String threadId;
    
    private Collection<StoryMetadata> metadata;

    @Override
    public String getUrl() {
        return "media/configure_to_story/?";
    }
    
    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    @SneakyThrows
    public String getPayload() {
        
        boolean direct = threadId != null;
        long time = System.currentTimeMillis();
        
        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_csrftoken", api.getOrFetchCsrf());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("upload_id", uploadId);
        
        Map<String, Object> deviceMap = new LinkedHashMap<>();
        deviceMap.put("manufacturer", InstagramConstants.DEVICE_MANUFACTURER);
        deviceMap.put("model", InstagramConstants.DEVICE_MODEL);
        deviceMap.put("android_version", InstagramConstants.DEVICE_ANDROID_VERSION);
        deviceMap.put("android_release", InstagramConstants.DEVICE_ANDROID_RELEASE);
        likeMap.put("device", deviceMap);
        
        BufferedImage image = MyImageUtils.getImage(mediaFile);
        
        Map<String, Object> editsMap = new LinkedHashMap<>();
        editsMap.put("crop_original_size", Arrays.asList((double) image.getWidth(), (double) image.getHeight()));
        editsMap.put("crop_center", Arrays.asList((double) 0, (double) -0));
        editsMap.put("crop_zoom", 1.0);
        likeMap.put("edits", editsMap);
        
        Map<String, Object> extraMap = new LinkedHashMap<>();
        extraMap.put("source_width", image.getWidth());
        extraMap.put("source_height", image.getHeight());
        likeMap.put("extra", extraMap);
        
        likeMap.put("client_shared_at", Long.toString(time - ThreadLocalRandom.current().nextInt(3, 10)));
        likeMap.put("source_type", "3");
        likeMap.put("configure_mode", direct ? "2" : "1");
        likeMap.put("story_media_creation_date", time - ThreadLocalRandom.current().nextInt(11, 20));
        likeMap.put("client_timestamp", Long.toString((time)));
        
        if(metadata != null) {
            applyMetadata(likeMap, metadata);
        }
        
        if(direct) {
            likeMap.put("thread_ids", new ObjectMapper().writeValueAsString(Arrays.asList(threadId)));
        }

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);
        
        return payloadJson;
    }

    @Override
    @SneakyThrows
    public InstagramConfigureStoryResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramConfigureStoryResult.class);
    }
    
    private void applyMetadata(Map<String, Object> toApplyTo, Collection<StoryMetadata> metadatas) {
        
        for(StoryMetadata metadata : metadatas) {
            if(metadata.check())
                toApplyTo.put(metadata.key(), metadata.metadata());
        }
        
        toApplyTo.put("mas_opt_in", "NOT_PROMPTED");
        
    }
}
