package org.brunocvcunha.instagram4j.storymetadata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLocation;

/**
 * Created by bvn13 on 02.06.2019.
 */
@Getter
@Setter
@ToString
public class StoryItemLocation {

    private double height;
    private int is_hidden;
    private int is_pinned;
    private int is_sticker;
    private InstagramLocation location;
    private double rotation;
    private double width;
    private double x;
    private double y;
    private double z;

}
