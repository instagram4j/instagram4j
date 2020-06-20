package com.github.instagram4j.Instagram4J.responses.media;

import com.github.instagram4j.Instagram4J.models.media.IGCarouselMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGMediaConfigureSidecarResponse extends IGResponse {
	private IGCarouselMedia media;
}