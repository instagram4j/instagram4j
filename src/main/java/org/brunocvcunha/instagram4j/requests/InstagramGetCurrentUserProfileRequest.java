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

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetCurrentUserProfileResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Get details about the currently logged in account.
 *
 * @author Alexander Kohonovsky
 * @since 2019-04-28
 */
@Log4j
@RequiredArgsConstructor
public class InstagramGetCurrentUserProfileRequest extends InstagramPostRequest<InstagramGetCurrentUserProfileResult> {

    @Override
    public String getUrl() {
        return "accounts/current_user/?edit=true";
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        Map<String, Object> map = new HashMap<>();
        map.put("_uuid", api.getUuid());
        map.put("_uid", api.getUserId());
        map.put("_csrftoken", api.getOrFetchCsrf());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

    @Override
    public InstagramGetCurrentUserProfileResult parseResult(int resultCode, String content) {
        return this.parseJson(content, InstagramGetCurrentUserProfileResult.class);
    }

}
