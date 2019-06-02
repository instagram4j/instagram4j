package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by bvn13 on 02.06.2019.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstagramLocationContainer {
    private InstagramItem header_media;
    private InstagramLocation location;
    private List<InstagramItem> media_bundles;
    private String subtitle;
    private String title;
}
