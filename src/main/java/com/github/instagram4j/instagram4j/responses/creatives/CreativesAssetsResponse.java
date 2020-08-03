package com.github.instagram4j.instagram4j.responses.creatives;

import java.util.List;

import com.github.instagram4j.instagram4j.models.creatives.StaticSticker;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class CreativesAssetsResponse extends IGResponse {
    private List<StaticSticker> static_stickers;
}
