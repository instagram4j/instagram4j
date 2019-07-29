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
import java.util.Map;

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
    public static final int PHOTO = 1;
    public static final int VIDEO = 2;
    public static final int ALBUM = 8;
    public long taken_at;
    public long pk;
    public String id;
    public long device_timestamp;
    public int media_type;
    public String code;
    public String client_cache_key;
    public int filter_type;
    public boolean has_audio;
    public double video_duration;
    public Map<String, Object> attribution;
    public List<ImageMeta> video_versions;
    public ImageVersions image_versions2;
    public int original_width;
    public int original_height;
    public int number_of_qualities;
    public InstagramUser user;
    public String organic_tracking_token;
    public boolean can_viewer_reshare;
    public boolean caption_is_edited;
    public InstagramComment caption;
    public boolean photo_of_you;
    public boolean comments_disabled;
    public boolean can_viewer_save;
    public boolean has_viewer_saved;
    private String visibility;
    private boolean is_reel_media;
    private boolean can_reply;
}
