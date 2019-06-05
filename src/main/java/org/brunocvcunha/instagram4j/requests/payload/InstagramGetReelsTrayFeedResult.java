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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Get Reels Tray Result (Story)
 *
 * @author Ozan Karaali
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramGetReelsTrayFeedResult extends StatusResult {
    private List<InstagramStoryTray> tray;
    private List<InstagramBroadcast> broadcasts;
    private InstagramPostLive post_live;
    private int sticker_version;
    private int face_filter_nux_version;
    private boolean has_new_nux_story;
    private String story_ranking_token;
    private boolean stories_viewer_gestures_nux_eligible;

}