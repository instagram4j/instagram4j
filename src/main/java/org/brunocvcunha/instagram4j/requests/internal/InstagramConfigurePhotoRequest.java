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
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureMediaResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;

/**
 * Like Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramConfigurePhotoRequest extends InstagramPostRequest<InstagramConfigureMediaResult> {

    private BufferedImage bufferedImage;
    private String uploadId;
    private String caption;

    public InstagramConfigurePhotoRequest(BufferedImage bufferedImage, String uploadId, String caption) {
        this.bufferedImage = bufferedImage;
        this.uploadId = uploadId;
        this.caption = caption;
    }

    public InstagramConfigurePhotoRequest(File file, String uploadId, String caption) throws IOException {
        this(ImageIO.read(file), uploadId, caption);
    }

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
        deviceMap.put("manufacturer", InstagramConstants.getDevice().getDEVICE_MANUFACTURER());
        deviceMap.put("model", InstagramConstants.getDevice().getDEVICE_MODEL());
        deviceMap.put("android_version", InstagramConstants.getDevice().getDEVICE_ANDROID_VERSION());
        deviceMap.put("android_release", InstagramConstants.getDevice().getDEVICE_ANDROID_RELEASE());
        likeMap.put("device", deviceMap);

        Map<String, Object> editsMap = new LinkedHashMap<>();
        editsMap.put("crop_original_size", Arrays.asList((double) bufferedImage.getWidth(), (double) bufferedImage.getHeight()));
        editsMap.put("crop_center", Arrays.asList((double) 0, (double) 0));
        editsMap.put("crop_zoom", 1.0);
        likeMap.put("edits", editsMap);
        
        Map<String, Object> extraMap = new LinkedHashMap<>();
        extraMap.put("source_width", bufferedImage.getWidth());
        extraMap.put("source_height", bufferedImage.getHeight());
        likeMap.put("extra", extraMap);

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public InstagramConfigureMediaResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramConfigureMediaResult.class);
    }

}
