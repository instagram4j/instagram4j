package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class InstagramGetFollowingActivityStoryArgs {

    private String text;
    private List<InstagramGetFollowingActivityArgsLink> links = null;
    private Long profile_id;
    private String profile_image;
    private Long timestamp;
    private String tuuid;
    private Map<String, Object> additionalProperties = new HashMap<>();


}
