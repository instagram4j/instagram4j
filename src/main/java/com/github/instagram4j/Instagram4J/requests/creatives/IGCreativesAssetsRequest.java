package com.github.instagram4j.Instagram4J.requests.creatives;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.creatives.IGCreativesAssetsResponse;

import lombok.Data;

public class IGCreativesAssetsRequest extends IGPostRequest<IGCreativesAssetsResponse> {

	@Override
	protected IGPayload getPayload() {
		return new CreativesAssetsPayload();
	}

	@Override
	public String path() {
		return "/creatives/assets/";
	}

	@Override
	public Class<IGCreativesAssetsResponse> getResponseType() {
		return IGCreativesAssetsResponse.class;
	}
	
	@Data
	public static class CreativesAssetsPayload extends IGPayload {
		private final String type = "static_stickers";
	}
}