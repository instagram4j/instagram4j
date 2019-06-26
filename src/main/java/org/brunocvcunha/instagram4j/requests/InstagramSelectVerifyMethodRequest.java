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
package org.brunocvcunha.instagram4j.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.payload.InstagramSelectVerifyMethodResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * InstagramSelectVerifyMethodRequest
 *
 * @author evosystem
 */
@AllArgsConstructor
public class InstagramSelectVerifyMethodRequest extends InstagramPostRequest<InstagramSelectVerifyMethodResult> {

    @NonNull
    private final String challengeUrl;

    private final int choice;

    @Override
    public String getUrl() {
        return challengeUrl;
    }

    @Override
    @SneakyThrows
    public String getPayload() {
        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("choice", choice);
        likeMap.put("_csrftoken", api.getOrFetchCsrf());
        likeMap.put("guid", api.getUuid());
        likeMap.put("device_id", api.getDeviceId());

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public InstagramSelectVerifyMethodResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramSelectVerifyMethodResult.class);
    }
}