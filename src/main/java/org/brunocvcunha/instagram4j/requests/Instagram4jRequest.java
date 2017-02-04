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

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public abstract class Instagram4jRequest {

    @Getter @Setter
    @JsonIgnore
    protected Instagram4j api;
    
    /**
     * @return the url
     */
    public abstract String getUrl();
    
    /**
     * @return the method
     */
    public abstract String getMethod();
    
    /**
     * @return the payload
     */
    public abstract String getPayload();
    
    /**
     * @return the result
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public abstract HttpResponse execute() throws ClientProtocolException, IOException;
    
    /**
     * Process response
     * @param response On Response
     */
    public void onResponse(HttpResponse response) {
        // not mandatory
    }
}
