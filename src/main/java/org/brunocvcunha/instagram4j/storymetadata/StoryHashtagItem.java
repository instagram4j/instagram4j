package org.brunocvcunha.instagram4j.storymetadata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryHashtagItem {
    private double x;
    private double y;
    private double z;
    private double width;
    private double height;
    private double rotation;
    private int is_pinned;
    private int is_hidden;
    private int is_sticker;
    private Hashtag hashtag;
    
    @Getter
    @Setter
    public static class Hashtag {
	private String name;
	private long id;
    }
}
