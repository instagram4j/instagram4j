package com.github.instagram4j.Instagram4J.responses.creatives;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.creatives.IGSticker;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGCreativesAssetsResponse extends IGResponse {
	List<IGSticker> static_stickers;
}
