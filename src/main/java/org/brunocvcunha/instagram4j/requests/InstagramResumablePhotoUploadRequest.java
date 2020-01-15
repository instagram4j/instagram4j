package org.brunocvcunha.instagram4j.requests;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.InstagramResumablePhotoUploadRequest.InstagramPhotoUploadResult;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@AllArgsConstructor
@Log4j
public class InstagramResumablePhotoUploadRequest extends InstagramPostRequest<InstagramPhotoUploadResult> {
	@NonNull
	private File file;
	@NonNull
	private String mediaType;
	private String uploadId;

	@Override
	public String getUrl() {
		return "rupload_igphoto/";
	}

	@Override
	public InstagramPhotoUploadResult execute() throws ClientProtocolException, IOException {
		uploadId = uploadId == null ? String.valueOf(System.currentTimeMillis()) : uploadId;
		String name = uploadId + "_0_" + file.hashCode();
		HttpPost post = this.createUploadPostRequest(rUploadParams(uploadId, mediaType), name, this.createFileEntity());
		try (CloseableHttpResponse res = api.getClient().execute(post)) {
			api.setLastResponse(res);
			
			return this.parseResult(res.getStatusLine().getStatusCode(), EntityUtils.toString(res.getEntity()));
		}
	}

	@Override
	public InstagramPhotoUploadResult parseResult(int resultCode, String content) {
		return this.parseJson(resultCode, content, InstagramPhotoUploadResult.class);
	}

	private HttpPost createUploadPostRequest(String rparam, String name, HttpEntity entity) {
		HttpPost post = new HttpPost("https://i.instagram.com/" + getUrl() + name);
		log.debug("Photo upload url: " + "https://i.instagram.com/" + getUrl() + name);
		post.addHeader("X-IG-Capabilities", InstagramConstants.DEVICE_CAPABILITIES);
		post.addHeader("X-IG-Connection-Type", "WIFI");
		post.addHeader("X-Instagram-Rupload-Params", rparam);
		post.addHeader("X_FB_WATERFALL_ID", UUID.randomUUID().toString());
		post.addHeader("Cookie2", "$Version=1");
		post.addHeader("Accept-Language", "en-US");
		post.addHeader("Accept-Encoding", "gzip");
		post.addHeader("X-Entity-Name", name);
		post.addHeader("X-Entity-Type", "image/jpeg");
		post.addHeader("X-Entity-Length", String.valueOf(entity.getContentLength()));
		post.addHeader("Offset", "0");
		post.addHeader("Content-Type", "application/octet-stream");
		post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
		post.setEntity(entity);

		return post;
	}

	protected static String rUploadParams(String uploadId, String mediaType) {
		return String.format(
				"{\"retry_context\":{\"num_step_auto_retry\":0,\"num_reupload\":0,\"num_step_manual_retry\":0},\"media_type\":\"%s\",\"upload_id\":\"%s\",\"xsharing_user_ids\":[],\" image_compression\":{\"lib_name\":\"moz\",\"lib_version\":\"3.1.m\",\"quality\":\"80\"}}",
				mediaType, uploadId);
	}

	private HttpEntity createFileEntity() {
		return new FileEntity(file, ContentType.APPLICATION_OCTET_STREAM);
	}
	
	@Getter
	@Setter
	public static class InstagramPhotoUploadResult extends StatusResult {
		private String upload_id;
	}

}
