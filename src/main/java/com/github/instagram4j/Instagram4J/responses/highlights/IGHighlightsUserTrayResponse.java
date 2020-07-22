package com.github.instagram4j.Instagram4J.responses.highlights;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.highlights.IGHighlight;
import com.github.instagram4j.Instagram4J.models.igtv.IGChannel;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGHighlightsUserTrayResponse extends IGResponse {
    private List<IGHighlight> tray;
    private IGChannel tv_channel;
}
