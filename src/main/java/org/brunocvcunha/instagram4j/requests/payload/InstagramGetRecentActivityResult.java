package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

/**
 * Recent Activity Results
 *
 * @author Anthony E Rodriguez
 *
 */
@Getter
@Setter
@ToString(callSuper = true)

public class InstagramGetRecentActivityResult {
    public List<InstagramRecentActivityArgs> old_stories;
}
