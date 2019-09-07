package org.brunocvcunha.instagram4j.storymetadata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryHashtagItem extends StoryItem {
    private Hashtag hashtag;
    
    @Getter
    @Setter
    public static class Hashtag {
	private String name;
	private long id;
    }
}
