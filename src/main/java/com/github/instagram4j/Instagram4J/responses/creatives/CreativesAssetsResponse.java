package com.github.instagram4j.Instagram4J.responses.creatives;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.creatives.StaticSticker;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class CreativesAssetsResponse extends IGResponse {
    private List<StaticSticker> static_stickers;
}
