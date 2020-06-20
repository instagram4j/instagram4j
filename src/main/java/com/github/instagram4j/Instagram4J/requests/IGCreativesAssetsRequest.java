package com.github.instagram4j.Instagram4J.requests;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

public class IGCreativesAssetsRequest extends IGPostRequest<IGResponse> {

	@Override
	protected IGPayload getPayload() {
		return new CreativesAssetsPayload();
	}

	@Override
	public String path() {
		return "/creatives/assets/";
	}

	@Override
	public Class<IGResponse> getResponseType() {
		return IGResponse.class;
	}
	
	@Data
	public static class CreativesAssetsPayload extends IGPayload {
		private final String type = "static_stickers";
	}
}