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
import lombok.SneakyThrows;
import org.brunocvcunha.instagram4j.requests.payload.InstagramStickerRequestResult;

import java.util.HashMap;
import java.util.Map;

/**
 * InstagramStickerRequest
 * @author Justin
 *
 */
public class InstagramStickerRequest extends InstagramPostRequest<InstagramStickerRequestResult> {

    @Override
    public String getUrl() {
        return "creatives/assets/";
    }
    
    @Override
    @SneakyThrows
    public String getPayload() {
        Map<String, Object>  listMap = new HashMap<>();
        listMap.put("_csrftoken", api.getOrFetchCsrf());
        listMap.put("_uuid", api.getUuid());
        listMap.put("_uid", api.getUserId());
        listMap.put("type", "static_stickers");
        return new ObjectMapper().writeValueAsString(listMap);
    }

    @Override
    public InstagramStickerRequestResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramStickerRequestResult.class);
    }

}
