package org.brunocvcunha.instagram4j.requests.payload;

import java.util.List;

import org.brunocvcunha.instagram4j.storymetadata.ReelMentionItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryCountdownItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryHashtagItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryLocationItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryPollItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryQuestionItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryQuestionResponderInfo;
import org.brunocvcunha.instagram4j.storymetadata.StorySliderItem;
import org.brunocvcunha.instagram4j.storymetadata.StorySliderVoterInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramStoryItem extends InstagramItem {
    private String adaction;
    private String link_text;
    private boolean story_is_saved_to_archive;
    private List<InstagramUser> viewers;
    private int viewer_count;
    private int viewer_cursor;
    private int total_viewer_count;
    private List<StoryHashtagItem> story_hashtags;
    private List<StoryPollItem> story_polls;
    private List<ReelMentionItem> reel_mentions;
    private List<StoryLocationItem> story_locations;
    private List<StoryCta> story_cta;
    private List<StoryCountdownItem> story_countdowns;
    private List<StoryQuestionItem> story_questions;
    private List<StoryQuestionResponderInfo> story_question_responder_infos;
    private List<StorySliderItem> story_sliders;
    private List<StorySliderVoterInfo> story_slider_voter_infos;
}
