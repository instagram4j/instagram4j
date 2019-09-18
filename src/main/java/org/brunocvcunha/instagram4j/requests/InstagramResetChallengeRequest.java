/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests;

import java.util.HashMap;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.payload.InstagramGetChallengeResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * InstagramRestChallengeRequest.
 *
 * @author evosystem
 */
@RequiredArgsConstructor
public class InstagramResetChallengeRequest extends InstagramPostRequest<InstagramGetChallengeResult> {

    @NonNull
    private final String challengeUrl;

    @Override
    public String getUrl() {
        return challengeUrl;
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        Map<String, Object> map = new HashMap<>();
        map.put("_csrftoken", api.getOrFetchCsrf());
        map.put("guid", api.getUuid());
        map.put("device_id", api.getDeviceId());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

    @Override
    public InstagramGetChallengeResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramGetChallengeResult.class);
    }
}