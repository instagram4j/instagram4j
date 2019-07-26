package org.brunocvcunha.instagram4j.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramStoryItem extends InstagramItem {
    private String adaction;
    private String link_text;
    private List<StoryCta> story_cta;
}
