package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Tag Feed User tag container (because usertags ist not a list, but usertags.in is)
 *
 * @author Willy Hille
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstagramUserTagsContainer {

    private List<InstagramFeedUserTag> in;
}
