package org.brunocvcunha.instagram4j.requests.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class InstagramGetFollowingActivityResponse {

    @JsonProperty("auto_load_more_enabled")
    private Boolean autoLoadMoreEnabled;
    @JsonProperty("next_max_id")
    private Long next_max_id;
    @JsonProperty("stories")
    private List<InstagramGetFollowingActivityStoryItem> stories = null;
    @JsonProperty("status")
    private String status;


}
