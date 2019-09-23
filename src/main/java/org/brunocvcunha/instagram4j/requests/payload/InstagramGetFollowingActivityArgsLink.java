package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InstagramGetFollowingActivityArgsLink {

    private Long start;
    private Long end;
    private String type;
    private String id;
}
