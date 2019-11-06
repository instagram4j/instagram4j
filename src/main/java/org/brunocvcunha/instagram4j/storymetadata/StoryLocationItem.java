package org.brunocvcunha.instagram4j.storymetadata;

import org.brunocvcunha.instagram4j.requests.payload.FeedItemLocation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryLocationItem extends StoryItem {
    private FeedItemLocation location;
}
