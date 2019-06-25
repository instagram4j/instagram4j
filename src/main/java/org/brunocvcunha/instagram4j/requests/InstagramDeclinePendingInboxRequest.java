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
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Decline Inbox Request, can be use for pending and inbox item
 * 
 * @author Anthony  Rodriguez
 *
 */
@AllArgsConstructor
@Log4j
public class InstagramDeclinePendingInboxRequest extends InstagramPostRequest<StatusResult> {

    private String threadId;

    @Override
    public String getUrl() {
        return "direct_v2/threads/"+threadId+"/decline/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        Map<String, Object> DeclineInbox = new LinkedHashMap<>();
        DeclineInbox.put("_uuid", api.getUuid());
        DeclineInbox.put("_uid", api.getUserId());
        DeclineInbox.put("_csrftoken", api.getOrFetchCsrf());
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(DeclineInbox);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }
}
