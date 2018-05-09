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

import java.util.List;

import org.brunocvcunha.instagram4j.storymetadata.StoryPollItem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * InstagramItem
 *
 * @author Ozan Karaali
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramItem extends StatusResult{
    private final int PHOTO = 1;
    private final int VIDEO = 2;
    private final int ALBUM = 8;
    private String pk;
    private String id;
    private String media_type;
    private String code;
    private String visibility;
    private String taken_at;
    private String device_timestamp;
    private String client_cache_key;
    private String filter_type;
    private InstagramUser user;
    private InstagramFeedUserTag usertags;
    //private InstagramMedia media;
   //private InstagramStory stories;
    private List<Integer> media_ids;
    private List<StoryPollItem> story_polls;
    private int media_id;
    private List<String> thumbnail_urls;
    private List<String> large_urls;
    private List<String> media_infos;
    private String value;
    private String collapse_comments;
}
