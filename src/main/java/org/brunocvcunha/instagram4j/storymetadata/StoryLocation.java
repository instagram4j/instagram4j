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
public class StoryLocation {

    private double lat;
    private double lng;
    private InstagramLocation location_dict;
    private String name;
    private long pk;
    private String profile_pic_url;
    private String profile_pic_username;
    private String short_name;
    private String type;

}
