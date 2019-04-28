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
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetCurrentUserProfileResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserGenderEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Edit instagram profile request.
 *
 * <p><b>Warning:</b> You must provide ALL parameters to this function.
 * The values which you provide will overwrite all current values on your profile.
 * You can use {@link InstagramGetCurrentUserProfileRequest} to see your current values first.
 *
 * @author Alexander Kohonovsky
 * @see InstagramGetCurrentUserProfileRequest
 * @since 2019-04-27
 */
@Getter
@Log4j
public class InstagramEditProfileRequest extends InstagramPostRequest<InstagramGetCurrentUserProfileResult> {

    /**
     * Full name. Use "" for nothing.
     */
    private final String fullName;

    /**
     * Optional. Rename your account to a new username which you've already verified.
     */
    private final String username;

    /**
     * Website URL. Use "" for nothing.
     */
    private final String website;

    /**
     * Biography text. Use "" for nothing.
     */
    private final String biography;

    /**
     * Email. Required!
     */
    private final String email;

    /**
     * Phone number. Use "" for nothing.
     */
    private final String phone;

    /**
     * Required!
     */
    private final InstagramUserGenderEnum gender;

    @Builder
    private InstagramEditProfileRequest(String biography,
                                        String fullName,
                                        String phone,
                                        String website,
                                        String username,
                                        String email,
                                        InstagramUserGenderEnum gender) {

        if (StringUtils.length(biography) > 150) {
            throw new IllegalArgumentException("biography cannot be longer than 150 chars.");
        }

        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("username cannot be empty.");
        }

        this.biography = biography;
        this.fullName = fullName;
        this.phone = phone;
        this.website = website;
        this.username = username;
        this.email = email;
        this.gender = gender;
    }

    @Override
    public String getUrl() {
        return "accounts/edit_profile/";
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        Map<String, Object> map = new HashMap<>();
        map.put("_uuid", api.getUuid());
        map.put("_uid", api.getUserId());
        map.put("_csrftoken", api.getOrFetchCsrf());
        map.put("first_name", fullName);
        map.put("username", username);
        map.put("external_url", website);
        map.put("biography", biography);
        map.put("email", email);
        map.put("phone_number", phone);
        map.put("gender", gender.getInstagramCode());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

    @Override
    public InstagramGetCurrentUserProfileResult parseResult(int resultCode, String content) {
        return this.parseJson(resultCode, content, InstagramGetCurrentUserProfileResult.class);
    }

}
