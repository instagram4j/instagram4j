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

/**
 * Broadcast
 *
 * @author Ozan Karaali
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramBroadcast extends StatusResult {
    private InstagramUser broacast_owner;
    private String broadcast_status;
    private String cover_frame_url;
    private String published_time;
    private String broadcast_message;
    private boolean muted;
    private String media_id;
    private String id;
    private String rtmp_playback_url;
    private String dash_abr_playback_url;
    private String dash_playback_url;
    private int ranked_position;
    private String organic_tracking_token;
    private int seen_ranked_position;
    private int viewer_count;
    private String dash_manifest;
    private String expire_at;
    private String encoding_tag;
    private int total_unique_viewer_count;
    private boolean internal_only;
    private int number_of_qualities;
}
