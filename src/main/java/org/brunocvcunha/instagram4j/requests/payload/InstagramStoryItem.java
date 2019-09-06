package org.brunocvcunha.instagram4j.requests.payload;

import java.util.List;

import org.brunocvcunha.instagram4j.storymetadata.ReelMentionItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryCountdownItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryHashtagItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryLocationItem;
import org.brunocvcunha.instagram4j.storymetadata.StoryPollItem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramStoryItem extends InstagramItem {
    private String adaction;
    private String link_text;
    private List<StoryHashtagItem> story_hashtags;
    private List<StoryPollItem> story_polls;
    private List<ReelMentionItem> reel_mentions;
    private List<StoryLocationItem> story_locations;
    private List<StoryCta> story_cta;
    private List<StoryCountdownItem> story_countdowns;
}
