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

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureAlbumResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    private Collection<String> uploadIds;
    
    @NonNull
    private String caption;
    
    @Override
    public String getUrl() {
        // TODO Auto-generated method stub
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
        //
        pMap.put("client_sidecar_id", System.currentTimeMillis());
        pMap.put("caption", caption);
        List<Object> children = new LinkedList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:d HH:mm:ss");
        String date = format.format(new Date(System.currentTimeMillis()));
        for(String id : uploadIds) {
            Map<String, Object> photoConfig = new HashMap<>();
            photoConfig.put("date_time_original", date);
            photoConfig.put("scene_type", 1);
            photoConfig.put("disable_comments", false);
            photoConfig.put("upload_id", id);
            photoConfig.put("source_type", 0);
            photoConfig.put("scene_capture_type", "standard");
            photoConfig.put("date_time_digitized", date);
            photoConfig.put("geotag_enabled", false);
            photoConfig.put("camera_position", "back");
            Map<String, Object> edits = new LinkedHashMap<>();
            edits.put("filter_strength", 1);
            edits.put("filter_name", "IGNormalFilter");
            photoConfig.put("edits", edits);
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

}
