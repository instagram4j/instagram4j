package org.brunocvcunha.instagram4j.storymetadata;

import java.util.List;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorySliderVoterInfo {
    private long slider_id;
    private List<SliderVoter> voters;
    private String max_id;
    private boolean more_available;
    private long latest_slider_vote_time;
    
    @Getter
    @Setter
    public static class SliderVoter {
	private InstagramUser user;
	private double vote;
	private long ts;
    }
}
