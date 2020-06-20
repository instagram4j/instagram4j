package com.github.instagram4j.Instagram4J.responses.media;

import com.github.instagram4j.Instagram4J.models.media.IGImageMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGMediaConfigureResponse extends IGResponse {
	private IGImageMedia media;
}