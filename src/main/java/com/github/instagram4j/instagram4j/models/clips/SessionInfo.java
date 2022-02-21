package com.github.instagram4j.instagram4j.models.clips;

import com.github.instagram4j.instagram4j.models.media.Media;
import lombok.Data;

import java.util.List;

@Data
public class SessionInfo {
    private String session_id;
    private List<Media> media_info;
}
