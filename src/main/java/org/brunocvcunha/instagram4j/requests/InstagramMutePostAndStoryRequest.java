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

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramPostCommentResult;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Comment Post Request
 *
 * @author Malloum LAYA &amp; Stephane Sabalot
 *
 */
@AllArgsConstructor
@Log4j
public class InstagramMutePostAndStoryRequest extends InstagramPostRequest<InstagramPostCommentResult> {

    private Long userId;

    @Override
    public String getUrl() {
        return "friendships/mute_posts_or_story_from_follow/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_csrftoken", api.getOrFetchCsrf());
        likeMap.put("target_reel_author_id", userId);
        likeMap.put("target_posts_author_id", userId);
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public InstagramPostCommentResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramPostCommentResult.class);
    }


}
