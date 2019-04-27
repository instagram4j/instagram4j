package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * support receiving links in direct messages
 * @author Willy Hille
 */
@Getter
@Setter
@ToString
public class DirectLink {

    public String text;
    public LinkContext link_context;
}
