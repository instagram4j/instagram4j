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

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.internal.InstagramContent;

/**
 * 
 * @author brunovolpato
 *
 */
public abstract class InstagramGetRequest<T extends InstagramContent> extends InstagramRequest<T> {

    protected String getApiUrl() {
        return InstagramConstants.API_URL;
    }

    @Override
    public String getMethod() {
        return "GET";
    }
    
    @Override
    public T execute() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(getApiUrl() + getUrl());
        prepareRequest(get);

        HttpResponseContainer container = performHttpRequest(get);

        T result = parseResult(container.getStatusCode(), container.getContent());
        result.setResponseContent(container.getContent());
        return result;
    }
    
    @Override
    public String getPayload() {
        return null;
    }

    
}
