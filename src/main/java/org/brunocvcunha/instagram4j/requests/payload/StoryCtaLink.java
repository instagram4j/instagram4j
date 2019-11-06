package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryCtaLink {
    public int linkType;
    public String webUri;
    public String deeplinkUri;
    public String redirectUri;
    public String tapAndHoldContext;
}
