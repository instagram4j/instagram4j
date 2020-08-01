package com.github.instagram4j.Instagram4J.responses.highlights;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.highlights.Highlight;
import com.github.instagram4j.Instagram4J.models.igtv.Channel;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class HighlightsUserTrayResponse extends IGResponse {
    private List<Highlight> tray;
    private Channel tv_channel;
}
