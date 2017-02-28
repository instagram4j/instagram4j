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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.inutils4j.MyImageUtils;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

/**
 * Like Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@AllArgsConstructor
@Log4j
public class InstagramConfigurePhotoRequest extends InstagramPostRequest<StatusResult> {

    private File mediaFile;
    private String uploadId;
    private String caption;
    
    @Override
    public String getUrl() {
        return "media/configure/?";
    }

    @Override
    @SneakyThrows
    public String getPayload() {
        
        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_csrftoken", api.getOrFetchCsrf());
        likeMap.put("media_folder", "Instagram");
        likeMap.put("source_type", 4);
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("caption", caption);
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
        editsMap.put("crop_center", Arrays.asList((double) 0, (double) 0));
        editsMap.put("crop_zoom", 1.0);
        likeMap.put("edits", editsMap);
        
        Map<String, Object> extraMap = new LinkedHashMap<>();
        extraMap.put("source_width", image.getWidth());
        extraMap.put("source_height", image.getHeight());
        likeMap.put("extra", extraMap);

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(content, StatusResult.class);
    }
    
}
