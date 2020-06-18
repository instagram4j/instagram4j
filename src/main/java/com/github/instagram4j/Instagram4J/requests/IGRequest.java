package com.github.instagram4j.Instagram4J.requests;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.IGConstants;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Setter;
import okhttp3.Request;

public abstract class IGRequest<T extends IGResponse> {
	@Setter
	protected IGClient client;

	public abstract String getUrl();

	public abstract Request formRequest();

	public abstract Class<T> getResponseType();

	public T parseResponse(String json) throws JsonProcessingException, IOException {
		T response = IGUtils.MAPPER.readValue(json,  this.getResponseType());
		response.setClient(client);
		return response;
	}

	public boolean isSigned() {
		return true;
	}
	
	public Request.Builder applyHeaders(Request.Builder req) {
		req.addHeader("Connection", "close");
		req.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		req.addHeader("Accept-Language", "en-US");
		req.addHeader("X-IG-Capabilities", IGConstants.DEVICE_CAPABILITIES);
		req.addHeader("X-IG-App-ID", IGConstants.APP_ID);
		req.addHeader("User-Agent", IGConstants.USER_AGENT);
		req.addHeader("X-IG-Connection-Type", "WIFI");
		req.addHeader("X-Ads-Opt-Out", "0");
		req.addHeader("X-CM-Bandwidth-KBPS", "-1.000");
		req.addHeader("X-CM-Latency", "-1.000");
		req.addHeader("X-IG-App-Locale", "en_US");
		req.addHeader("X-IG-Device-Locale", "en_US");
		req.addHeader("X-Pigeon-Session-Id", IGUtils.randomUuid());
		req.addHeader("X-Pigeon-Rawclienttime", System.currentTimeMillis() + "");
		req.addHeader("X-IG-Connection-Speed", ThreadLocalRandom.current().nextInt(2000, 4000) + "kbps");
		req.addHeader("X-IG-Bandwidth-Speed-KBPS", "-1.000");
		req.addHeader("X-IG-Bandwidth-TotalBytes-B", "0");
		req.addHeader("X-IG-Bandwidth-TotalTime-MS", "0");
		req.addHeader("X-IG-Extended-CDN-Thumbnail-Cache-Busting-Value", "1000");
		req.addHeader("X-FB-HTTP-engine", "Liger");

		return req;
	}
	
}
