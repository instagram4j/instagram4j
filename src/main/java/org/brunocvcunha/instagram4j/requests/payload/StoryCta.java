package org.brunocvcunha.instagram4j.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryCta {
    private List<StoryCtaLink> links;
}
