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
package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramSearchUsersResultUser {

    public int unseen_count;
    public String username;
    public String byline;
    public String profile_pic_url;
    public boolean has_anonymous_profile_picture;
    public Map<String, Object> friendship_status;
    public boolean is_private;
    public boolean is_verified;
    public int mutual_followers_count;
    public String full_name;
    public long pk;
    public int follower_count;
    public String profile_pic_id;
    public String social_context;
    public String search_social_context;

}