package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Data;

@Data
public class InstagramSearchTagsResultTag {

    public String name;
    public long id;
    public int media_count;

}