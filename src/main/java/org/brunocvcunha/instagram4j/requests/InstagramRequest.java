/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.instagram4j.util.InstagramGenericUtil;
import org.brunocvcunha.inutils4j.MyStreamUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@NoArgsConstructor
@Log4j
public abstract class InstagramRequest<T> {

	@Getter
	@Setter
	@JsonIgnore
	protected Instagram4j api;

	/**
	 * @return the url
	 */
	public abstract String getUrl();

	/**
	 * @return the method
	 */
	public abstract String getMethod();

	/**
	 * @return the payload
	 */
	public String getPayload() {
		return null;
	}

	public HttpEntity getPayloadEntity() throws UnsupportedEncodingException {
		return null;
	}

	/**
	 * @return the result
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public abstract T execute() throws ClientProtocolException, IOException;

	/**
	 * Process response
	 * 
	 * @param resultCode Status Code
	 * @param content    Content
	 */
	public abstract T parseResult(int resultCode, String content);

	/**
	 * @return if request must be logged in
	 */
	public boolean requiresLogin() {
		return true;
	}

	/**
	 * Parses Json into type, considering the status code
	 * 
	 * @param statusCode HTTP Status Code
	 * @param str        Entity content
	 * @param clazz      Class
	 * @return Result
	 */
	@SneakyThrows
	public <U> U parseJson(int statusCode, String str, Class<U> clazz) {

		if (clazz.isAssignableFrom(StatusResult.class)) {

			// TODO: implement a better way to handle exceptions
			if (statusCode == HttpStatus.SC_NOT_FOUND) {
				StatusResult result = (StatusResult) clazz.newInstance();
				result.setStatus("error");
				result.setMessage("SC_NOT_FOUND");
				return (U) result;
			} else if (statusCode == HttpStatus.SC_FORBIDDEN) {
				StatusResult result = (StatusResult) clazz.newInstance();
				result.setStatus("error");
				result.setMessage("SC_FORBIDDEN");
				return (U) result;
			}
		}

		return parseJson(str, clazz);
	}

	/**
	 * Parses Json into type
	 * 
	 * @param str   Entity content
	 * @param clazz Class
	 * @return Result
	 */
	@SneakyThrows
	public <U> U parseJson(String str, Class<U> clazz) {

		if (log.isInfoEnabled()) {

			if (log.isDebugEnabled()) {
				log.debug("Reading " + clazz.getSimpleName() + " from " + str);
			} else {
				String printStr = str;
				if (printStr.length() > 128) {
					printStr = printStr.substring(0, 128);
				}
				log.info("Reading " + clazz.getSimpleName() + " from " + printStr);
			}

		}

		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		return objectMapper.readValue(str, clazz);
	}

	/**
	 * Parses Json into type
	 * 
	 * @param is    Entity stream
	 * @param clazz Class
	 * @return Result
	 */
	@SneakyThrows
	public T parseJson(InputStream is, Class<T> clazz) {
		return this.parseJson(MyStreamUtils.readContent(is), clazz);
	}

	/**
	 * @return payload should be signed
	 */
	public boolean isSigned() {
		return true;
	}

	public <E extends HttpRequest> E applyHeaders(E req) {

		req.setHeader("Connection", "close");
		req.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		req.setHeader("Accept-Language", "en-US");
		req.setHeader("Authorization", api.getAUTH_VALUE());
		req.setHeader("X-IG-Capabilities", InstagramConstants.DEVICE_CAPABILITIES);
		req.setHeader("X-IG-App-ID", InstagramConstants.APP_ID);
		req.setHeader("User-Agent", InstagramConstants.USER_AGENT);
		req.setHeader("X-IG-Connection-Type", "WIFI");
		req.setHeader("X-Ads-Opt-Out", "0");
		req.setHeader("X-CM-Bandwidth-KBPS", "-1.000");
		req.setHeader("X-CM-Latency", "-1.000");
		req.setHeader("X-IG-App-Locale", "en_US");
		req.setHeader("X-IG-Device-Locale", "en_US");
		req.setHeader("X-Pigeon-Session-Id", InstagramGenericUtil.generateUuid(true));
		req.setHeader("X-Pigeon-Rawclienttime", System.currentTimeMillis() + "");
		req.setHeader("X-IG-Connection-Speed", ThreadLocalRandom.current().nextInt(2000, 4000) + "kbps");
		req.setHeader("X-IG-Bandwidth-Speed-KBPS", "-1.000");
		req.setHeader("X-IG-Bandwidth-TotalBytes-B", "0");
		req.setHeader("X-IG-Bandwidth-TotalTime-MS", "0");
		req.setHeader("X-IG-EU-DC-ENABLED", null);
		req.setHeader("X-IG-Extended-CDN-Thumbnail-Cache-Busting-Value", "1000");
		req.setHeader("X-MID", api.getX_MID());
		req.setHeader("X-IG-WWW-Claim", api.getWWW_CLAIM());
		req.setHeader("X-FB-HTTP-engine", "Liger");

		return req;
	}

}
