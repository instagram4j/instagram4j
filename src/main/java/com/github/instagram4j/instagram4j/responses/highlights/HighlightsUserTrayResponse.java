package com.github.instagram4j.instagram4j.responses.highlights;

import java.util.List;

import com.github.instagram4j.instagram4j.models.highlights.Highlight;
import com.github.instagram4j.instagram4j.models.igtv.Channel;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class HighlightsUserTrayResponse extends IGResponse {
    private List<Highlight> tray;
    private Channel tv_channel;
}
