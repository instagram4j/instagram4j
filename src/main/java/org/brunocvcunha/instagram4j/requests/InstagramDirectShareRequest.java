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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.instagram4j.util.InstagramGenericUtil;

import lombok.Builder;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

/**
 * Direct-share request.
 * 
 * @author Evgeny Bondarenko (evgbondarenko@gmail.com)
 *
 */
@Log4j
@Builder(builderClassName = "DirectShareRequestBuilder", builderMethodName = "internalBuilder")
public class InstagramDirectShareRequest extends InstagramRequest<StatusResult> {
	@NonNull
	private ShareType shareType;
	/**
	 * List of recipients IDs (i.e. "1234567890")
	 */
	private List<String> recipients;
	/**
	 * The thread ID to share to
	 */
	private String threadId;
	/**
	 * The media ID in instagram's internal format (ie "223322332233_22332").
	 */
	private String mediaId;
	private String message;

	@Override
	public String getUrl() throws IllegalArgumentException {
		String result;
		switch (shareType) {
		case MESSAGE:
			result = "direct_v2/threads/broadcast/text/";
			break;
		case MEDIA:
			result = "direct_v2/threads/broadcast/media_share/?media_type=photo";
			break;
		default:
			throw new IllegalArgumentException("Invalid shareType parameter value: " + shareType);
		}
		return result;
	}

	@Override
	public String getMethod() {
		return "POST";
	}

	@Override
	public StatusResult execute() throws ClientProtocolException, IOException {
		String recipients = "";
		if (this.recipients != null) {
			recipients = "\"" + String.join("\",\"", this.recipients.toArray(new String[0])) + "\"";
		}
		
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();
		if (shareType == ShareType.MEDIA) {
			map.put("type", "form-data");
			map.put("name", "media_id");
			map.put("data", mediaId);
			data.add(map);
		}

		map = map.size() > 0 ? new HashMap<String, String>() : map;
		map.put("type", "form-data");
		map.put("name", "recipient_users");
		map.put("data", "[[" + recipients + "]]");
		data.add(map);

		map = new HashMap<String, String>();
		map.put("type", "form-data");
		map.put("name", "client_context");
		map.put("data", InstagramGenericUtil.generateUuid(true));
		data.add(map);

		map = new HashMap<String, String>();
		map.put("type", "form-data");
		map.put("name", "thread_ids");
		map.put("data", "[" + (threadId != null ? threadId : "") + "]");
		data.add(map);

		map = new HashMap<String, String>();
		map.put("type", "form-data");
		map.put("name", "text");
		map.put("data", message == null ? "" : message);
		data.add(map);

		HttpPost post = createHttpRequest();
		post.setEntity(new ByteArrayEntity(buildBody(data, api.getUuid()).getBytes(StandardCharsets.UTF_8)));

		try (CloseableHttpResponse response = api.getClient().execute(post)) {
			api.setLastResponse(response);

			int resultCode = response.getStatusLine().getStatusCode();
			String content = EntityUtils.toString(response.getEntity());

			log.info("Direct-share request result: " + resultCode + ", " + content);

			post.releaseConnection();

			StatusResult result = parseResult(resultCode, content);

			return result;
		}
	}

	@Override
	public StatusResult parseResult(int resultCode, String content) {
		return parseJson(resultCode, content, StatusResult.class);
	}

	protected HttpPost createHttpRequest() {
		String url = InstagramConstants.API_URL + getUrl();
		log.info("Direct-share URL: " + url);

		HttpPost post = new HttpPost(url);
		post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
		post.addHeader("Connection", "keep-alive");
		post.addHeader("Proxy-Connection", "keep-alive");
		post.addHeader("Accept", "*/*");
		post.addHeader("Content-Type", "multipart/form-data; boundary=" + api.getUuid());
		post.addHeader("Accept-Language", "en-US");
		return post;
	}

	protected String buildBody(List<Map<String, String>> bodies, String boundary) {
		StringBuilder sb = new StringBuilder();
		String newLine = "\r\n";
		for (Map<String, String> b : bodies) {
			sb.append("--").append(boundary).append(newLine).append("Content-Disposition: ").append(b.get("type"))
					.append("; name=\"").append(b.get("name")).append("\"").append(newLine).append(newLine)
					.append(b.get("data")).append(newLine);
		}
		sb.append("--").append(boundary).append("--");
		String body = sb.toString();

		log.debug("Direct-share message body: " + body);
		return body;
	}

	protected void init() {
		switch (shareType) {
		case MEDIA:
			if (mediaId == null || mediaId.isEmpty()) {
				throw new IllegalArgumentException("mediaId cannot be null or empty.");
			}
			break;
		case MESSAGE:
			if (message == null || message.isEmpty()) {
				throw new IllegalArgumentException("message cannot be null or empty.");
			}
			break;
		default:
			break;
		}
	}

	public static Builder builder(ShareType shareType) {
		Builder b = new Builder();
		b.shareType(shareType);
		return b;
	}
	
	public static Builder builder(ShareType shareType, List<String> recipients) {
		Builder b = new Builder();
		b.shareType(shareType).recipients(recipients);
		return b;
	}

	public static class Builder extends DirectShareRequestBuilder {
		Builder() {
			super();
		}

		@Override
		public InstagramDirectShareRequest build() {
			InstagramDirectShareRequest i = super.build();
			i.init();
			return i;
		}
	}

	public enum ShareType {
		MESSAGE, MEDIA
	}
}
