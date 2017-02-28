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

import org.apache.http.HttpResponse;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.instagram4j.util.InstagramGenericUtil;

import lombok.SneakyThrows;

/**
 * Recent Activity Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramGetRecentActivityRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "news/inbox/?";
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(content, StatusResult.class);
    }

}
