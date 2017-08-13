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

import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

import lombok.SneakyThrows;

/**
 * Timeline Feed Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramTimelineFeedRequest extends InstagramGetRequest<StatusResult> {

    private String maxId;
    
    @Override
    public String getUrl() {
        String url = "feed/timeline/";
        if (maxId != null && !maxId.isEmpty()) {
            url += "&max_id=" + maxId;
        }
        
        return url;
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
