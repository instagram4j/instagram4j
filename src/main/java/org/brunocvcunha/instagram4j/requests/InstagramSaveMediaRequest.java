/**
 * Copyright (C) 2018 Zsombor Gegesy (gzsombor@gmail.com)
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
package org.brunocvcunha.instagram4j.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Request to save a media into the saved media list by it's numerical id.
 *
 * @author zsombor
 */
@AllArgsConstructor
public class InstagramSaveMediaRequest extends InstagramPostRequest<StatusResult> {

    protected long mediaId;

    @Override
    public String getUrl() {
        return "media/" + mediaId +"/save/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {
        
        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_csrftoken", api.getOrFetchCsrf());
        likeMap.put("media_id", mediaId);
        
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
