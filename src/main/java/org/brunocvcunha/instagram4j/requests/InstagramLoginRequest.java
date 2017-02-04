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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginPayload;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

/**
 * 
 * @author brunovolpato
 *
 */
@AllArgsConstructor
@Log4j
public class InstagramLoginRequest extends Instagram4jPostRequest {

    private InstagramLoginPayload payload;
    
    @Override
    @JsonIgnore
    public String getUrl() {
        return "accounts/login/";
    }

    @Override
    @JsonIgnore
    @SneakyThrows
    public String getPayload() {
        
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(payload);
        log.info("Payload: " + payloadJson);
        
        return payloadJson;
    }

    @Override
    public void onResponse(HttpResponse response) {
        
    }
}

