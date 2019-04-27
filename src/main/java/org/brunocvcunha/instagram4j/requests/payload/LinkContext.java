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
public class LinkContext {

    public String link_url;
    public String link_title;
    public String link_summary;
    public String link_image_url;
}
