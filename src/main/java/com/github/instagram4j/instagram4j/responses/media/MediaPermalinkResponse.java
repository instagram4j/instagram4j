package com.github.instagram4j.instagram4j.responses.media;

import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Data;

@Data
public class MediaPermalinkResponse extends IGResponse {
    private String permalink;
}
