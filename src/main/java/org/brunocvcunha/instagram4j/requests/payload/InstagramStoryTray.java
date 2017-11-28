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

import java.util.List;

/**
 * StoryTray
 *
 * @author Ozan Karaali
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramStoryTray {
    private String id;
    private List<Object> items; //Item[]
    private InstagramUser user;
    private boolean can_reply; //boolean
    private String expiring_at; //long
    private Object seen_ranked_position;
    private String seen;
    private String latest_reel_media;
    private Object ranked_position;
    private Object is_nux;
    private Object show_nux_tooltip;
    private Object muted;
    private Object prefetch_count;
    private Object location; //Location
    private Object owner; //Owner
    private String nux_id;
    private Object dismiss_card; //DismissCard
    private Object can_reshare;
    private boolean has_besties_media;
    private String reel_type;
}