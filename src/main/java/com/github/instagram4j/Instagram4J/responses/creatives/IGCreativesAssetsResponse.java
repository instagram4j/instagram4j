package com.github.instagram4j.Instagram4J.responses.creatives;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.creatives.IGStaticSticker;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGCreativesAssetsResponse extends IGResponse {
	private List<IGStaticSticker> static_stickers;
}
