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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.instagram4j.util.InstagramGenericUtil;
import org.brunocvcunha.inutils4j.MyImageUtils;

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
	@NonNull
	private List<String> recipients;
	/**
	 * The thread ID to share to
	 */
	private String threadId;
	/**
	 * Text field that if is populated will be appended to share request as message
	 */
	private String text;
	/**
	 * {@link ShareType#VIDEO} fields
	 * 
	 * mediaId {@link NonNull}
	 * videoResult
	 */
	private String mediaId;
	private String videoResult;
	/**
	 * {@link ShareType#MESSAGE} fields
	 * 
	 * message {@link NonNull}
	 */
	private String message;
	
	/**
	 * {@link ShareType#HASHTAG} fields
	 * 
	 * hashtag {@link NonNull}
	 */
	private String hashtag;
	/**
	 * {@link ShareType#LOCATION} fields
	 * 
	 * venueId {@link NonNull}
	 */
	private String venueId;
	/**
	 * {@link ShareType#PROFILE} fields
	 * 
	 * userId {@link NonNull}
	 */
	private String userId;
	/**
	 * {@link ShareType#PHOTO} fields
	 * 
	 * photoFilePath {@link NonNull}
	 * photoFile
	 * photoArray
	 * uploadId
	 */
	private String photoFilePath;
	private File photoFile;
	private byte[] photoArray;
	private String uploadId;
	/**
	 * 
	 * {@link ShareType#LINKS} fields
	 * 
	 * link_urls {@link NonNull}
	 * link_text 
	 */
	private List<String> linkUrls;
	private String linkText;
	/**
	 * 
	 * {@link ShareType#LIVE} fields
	 * 
	 * broadcastId {@link NonNull}
	 */
	private String broadcastId;
	/**
	 * 
	 * {@link ShareType#REACTION} fields
	 * 
	 * reactionType
	 * reactionStatus
	 * itemId
	 * nodeType
	 */
	private String reactionType;
	private String reactionStatus;
	private String itemId;
	private String nodeType;

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
		case LIKE:
			result = "direct_v2/threads/broadcast/like/";
			break;
		case HASHTAG:
			result = "direct_v2/threads/broadcast/hashtag/";
			break;
		case LOCATION:
			result = "direct_v2/threads/broadcast/location/";
			break;
		case PROFILE:
			result = "direct_v2/threads/broadcast/profile/";
			break;
		case PHOTO:
			result = "direct_v2/threads/broadcast/upload_photo/";
			break;
		case VIDEO:
			result = "direct_v2/threads/broadcast/configure_video/";
			break;
		case LINKS:
			result = "direct_v2/threads/broadcast/link/";
			break;
		case REACTION:
			result = "direct_v2/threads/broadcast/reaction/";
			break;
		case LIVE:
			result = "direct_v2/threads/broadcast/live_viewer_invite/";
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
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		switch(shareType) {
			case MEDIA:
				builder.addTextBody("media_id", mediaId);
				break;
			case MESSAGE:
		        builder.addTextBody("text", message);
				break;
			case HASHTAG:
				builder.addTextBody("hashtag", hashtag);
				break;
			case LOCATION:
				builder.addTextBody("venue_id", venueId);
				break;
			case PROFILE:
				builder.addTextBody("profile_user_id", userId);
				break;
			case PHOTO:
			    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			            ImageIO.write(MyImageUtils.getImage(photoFile), "jpg", baos);
			            baos.flush();
			            photoArray = baos.toByteArray();
			            this.uploadId = String.valueOf(System.currentTimeMillis());
			    }
				builder.addBinaryBody("photo", photoArray, ContentType.APPLICATION_OCTET_STREAM, "pending_media_" + uploadId + ".jpg");
				break;
			case VIDEO:
				builder.addTextBody("upload_id", mediaId);
				if(videoResult != null)
					builder.addTextBody("video_result", videoResult);
				break;
			case LINKS:
				String linkUrlsAsString = "\"" + String.join("\",\"", this.linkUrls.toArray(new String[0])) + "\"";
				
				builder.addTextBody("link_urls", "[" + linkUrlsAsString + "]");
		        builder.addTextBody("link_text", linkText);
				break;
			case REACTION:
				builder.addTextBody("reaction_type", reactionType);
				builder.addTextBody("reaction_status", reactionStatus);
				builder.addTextBody("item_id", itemId);
				builder.addTextBody("node_type", nodeType);
				break;
			case LIVE:
				builder.addTextBody("broadcast_id", broadcastId);
				break;
			default:
				break;
		}
		
		if(this.text != null)
			builder.addTextBody("text", text);
		
		builder.addTextBody("recipient_users", "[[" + recipients + "]]");
		builder.addTextBody("client_context", InstagramGenericUtil.generateUuid(true));		
		builder.addTextBody("thread_ids", "[" + (threadId != null ? threadId : "") + "]");
		builder.setBoundary(api.getUuid());
		
		HttpPost post = createHttpRequest();
		post.setEntity(builder.build());
		
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
		case HASHTAG:
			if (hashtag == null || hashtag.isEmpty()) {
				throw new IllegalArgumentException("hashtag cannot be null or empty.");
			}
			break;
		case LOCATION:
			if (venueId == null || venueId.isEmpty()) {
				throw new IllegalArgumentException("venueId cannot be null or empty.");
			}
			break;
		case PROFILE:
			if (userId == null || userId.isEmpty()) {
				throw new IllegalArgumentException("userId cannot be null or empty.");
			}
			break;
		case PHOTO:
			if (photoFilePath == null || photoFilePath.isEmpty()) {
				throw new IllegalArgumentException("photoFilePath cannot be null or empty.");
			}
			this.photoFile = new File(this.photoFilePath);
			break;
		case VIDEO:
			if (mediaId == null || mediaId.isEmpty()) {
				throw new IllegalArgumentException("mediaId cannot be null or empty.");
			}
			break;
		case LINKS:
			if (linkUrls == null || linkUrls.isEmpty()) {
				throw new IllegalArgumentException("linkUrls cannot be null or empty.");
			}
			if(linkText == null || linkText.isEmpty()) {
				throw new IllegalArgumentException("linkText cannot be null or empty.");
			}
			break;
		case REACTION:
			if(reactionType == null || reactionType.isEmpty()) {
				throw new IllegalArgumentException("reactionType cannot be null or empty.");
			}
			if(reactionStatus == null || reactionStatus.isEmpty()) {
				throw new IllegalArgumentException("reactionStatus cannot be null or empty.");
			}
			if(itemId == null || itemId.isEmpty()) {
				throw new IllegalArgumentException("itemId cannot be null or empty.");
			}
			if(nodeType == null || nodeType.isEmpty()) {
				throw new IllegalArgumentException("nodeType cannot be null or empty.");
			}
			break;
		case LIVE:
			if (broadcastId == null || broadcastId.isEmpty()) {
				throw new IllegalArgumentException("broadcastId cannot be null or empty.");
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
		MEDIA,
		MESSAGE,
		LIKE,
		HASHTAG,
		LOCATION,
		PROFILE,
		PHOTO,
		VIDEO,
		LINKS,
		REACTION,
		LIVE 
	}
}
