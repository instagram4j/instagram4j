package org.brunocvcunha.instagram4j.storymetadata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorySliderItem extends StoryItem {
    private SliderSticker slider_sticker;
    
    @Getter
    @Setter
    public static class SliderSticker {
	private long slider_id;
	private String question;
	private String emoji;
	private String text_color;
	private String background_color;
	private boolean viewer_can_vote;
	private double slider_vote_average;
	private int slider_vote_count;
	private double viewer_vote;
    }
}
