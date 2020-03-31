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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureAlbumResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

/**
 * InstagramConfigureAlbumRequest
 * 
 * @author Justin Vo
 *
 */

@RequiredArgsConstructor
public class InstagramConfigureAlbumRequest extends InstagramPostRequest<InstagramConfigureAlbumResult> {
    @NonNull
    private List<AlbumChildrenMetadata> children_meta;
    @NonNull
    private String caption;
    
    @Override
    public String getUrl() {
        return "media/configure_sidecar/";
    }
    
    @Override
    public String getMethod() {
        return "POST";
    }
    
    @Override
    @SneakyThrows
    public String getPayload() {
        Map<String, Object> pMap = new LinkedHashMap<>();
        pMap.put("_csrftoken", api.getOrFetchCsrf());
        pMap.put("_uid", api.getUserId());
        pMap.put("_uuid", api.getUuid());
        pMap.put("client_sidecar_id", System.currentTimeMillis());
        pMap.put("caption", caption);
        List<Object> children = new ArrayList<>();
        for(AlbumChildrenMetadata id : children_meta) {
            Map<String, Object> photoConfig = new HashMap<>();
            photoConfig.put("upload_id", id.getUploadId());
            photoConfig.put("height", id.getHeight());
            photoConfig.put("width", id.getWidth());
            if(id.isVideo()) {
            	photoConfig.put("length", id.getDuration());
            }
            children.add(photoConfig);
        }
        pMap.put("children_metadata", children);
        
        ObjectMapper mapper = new ObjectMapper();
        
        return mapper.writeValueAsString(pMap);
    }

    @Override
    public InstagramConfigureAlbumResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramConfigureAlbumResult.class);
    }
    
    @Getter
    @Setter
    @Builder
    public static class AlbumChildrenMetadata {
    	private String uploadId;
    	@Builder.Default
    	private boolean isVideo = false;
    	private double height;
    	private double width;
    	private long duration;
    }

}
