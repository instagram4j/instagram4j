package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramConfigureMediaResult extends StatusResult {
	private InstagramFeedItem media;
	private String uploadId;
}
