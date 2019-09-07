package org.brunocvcunha.instagram4j.storymetadata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryQuestionItem extends StoryItem {
    private QuestionSticker question_sticker;
    
    @Getter
    @Setter
    public static class QuestionSticker {
	private String question_type;
	private long question_id;
	private String question;
	private long media_id;
	private String text_color;
	private String background_color;
	private boolean viewer_can_interact;
	private String profile_pic_url;
    }
}
