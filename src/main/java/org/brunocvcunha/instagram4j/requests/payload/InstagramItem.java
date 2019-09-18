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

import org.brunocvcunha.instagram4j.storymetadata.ImageVersions2;
import org.brunocvcunha.instagram4j.storymetadata.StoryFeedMedia;
import org.brunocvcunha.instagram4j.storymetadata.StoryPollItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryPollVoterInfos;
import org.brunocvcunha.instagram4j.storymetadata.StoryQuestionItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryQuestionResponderInfos;
import org.brunocvcunha.instagram4j.storymetadata.StorySliderItem;
import org.brunocvcunha.instagram4j.storymetadata.StorySliderVoterInfos;
import org.brunocvcunha.instagram4j.storymetadata.VideoVersion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * InstagramItem
 *
 * @author Ozan Karaali &
 * @author George Chousos 💛 gxousos@gmail.com
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
    private int filter_type;

    
    private ImageVersions2 image_versions2;
    private int original_width;
    private int original_height;
    
    private List<VideoVersion> video_versions;
    private Boolean has_audio;
    private double video_duration;
    
    private InstagramUser user;
    
    private Boolean caption_is_edited;
    private double caption_position;
    private Boolean is_reel_media;
    private long timezone_offset; // int ?
    private Boolean photo_of_you;
    private String caption;
    private Boolean can_viewer_save;
    private String organic_tracking_token;
    private long expiring_at;
    private Boolean can_reshare;
    private Boolean can_reply;
    
    private List<StoryFeedMedia> story_feed_media;
    
    private Boolean story_is_saved_to_archive;
    private List<InstagramUserSummary> viewers;

    //private StoryAppAttribution story_app_attribution // Not sure fo something ...
    
    private int viewer_count;
    private String viewer_cursor;
    private int total_viewer_count;
    //private List<> multi_author_reel_names;
    private Boolean supports_reel_reactions;
    private Boolean show_one_tap_fb_share_tooltip;
    private int has_shared_to_fb; // int?
    
    
    
    
    
    //private InstagramFeedUserTag usertags;
    //private InstagramMedia media;
    //private InstagramStory stories;
    private List<Integer> media_ids;
    private List<StoryPollItem> story_polls;
    private List<StorySliderItem> story_sliders;
    private List<StoryQuestionItem> story_questions;
    private List<StoryPollVoterInfos> story_poll_voter_infos;
    private List<StorySliderVoterInfos> story_slider_voter_infos;
    private List<StoryQuestionResponderInfos> story_question_responder_infos;

    private int media_id;
    private String thumbnail_urls;
    private String large_urls;
    private String media_infos;
    private String value;
    private String collapse_comments;
}
