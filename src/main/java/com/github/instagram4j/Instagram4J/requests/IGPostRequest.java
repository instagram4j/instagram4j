package com.github.instagram4j.Instagram4J.requests;

import com.github.instagram4j.Instagram4J.IGConstants;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class IGPostRequest<T> extends IGRequest<T> {
	@Override
	public Request formRequest() {
		Request.Builder req = new Request.Builder().url(IGConstants.API_URL + this.getUrl());
		this.applyHeaders(req);
		req.post(this.getRequestBody());
		
		return req.build();
	}
	
	private RequestBody getRequestBody() {
		if (isSigned()) {
			return RequestBody.create(IGUtils.generateSignature(getPayload()), MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8"));
		} else {
			return RequestBody.create(getPayload(), MediaType.parse("application/json; charset=UTF-8"));
		}
	}

}
