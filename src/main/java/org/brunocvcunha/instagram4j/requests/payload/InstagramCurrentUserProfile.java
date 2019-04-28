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
package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Alexander Kohonovsky
 * @since 2019-04-28
 */
@Getter
@Setter
public class InstagramCurrentUserProfile {

    private long pk;
    private String username;
    private String full_name;
    private boolean is_private;
    private String profile_pic_url;
    private boolean is_verified;
    private boolean has_anonymous_profile_picture;
    private String biography;
    private String external_url;

    /**
     * Auto archive stories for viewing them later.
     * It will appear in your archive once it has disappeared from your story feed.
     * Possible values: "unset" (initial value), "on", "off"
     */
    private String reel_auto_archive;
    private List<InstagramProfilePic> hd_profile_pic_versions;
    private InstagramProfilePic hd_profile_pic_url_info;
    private boolean show_conversion_edit_entry;

    /**
     * Possible values: "any", ...
     */
    private String allowed_commenter_type;

    private String birthday;

    private String phone_number;
    private String country_code;
    private Long national_number;

    private InstagramUserGenderEnum gender;
    private String email;
    private boolean can_link_entities_in_bio;

}
