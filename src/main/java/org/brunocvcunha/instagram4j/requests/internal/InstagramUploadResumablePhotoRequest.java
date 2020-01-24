package org.brunocvcunha.instagram4j.requests.internal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadResumablePhotoRequest.InstagramUploadPhotoResult;
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
public class InstagramUploadResumablePhotoRequest extends InstagramPostRequest<InstagramUploadPhotoResult> {
	@NonNull
	private File file;
	@NonNull
	private String mediaType;
	private String uploadId;
	private boolean isSideCar;
	
	@Override
	public String getUrl() {
		return "rupload_igphoto/";
	}

	@Override
	public InstagramUploadPhotoResult execute() throws ClientProtocolException, IOException {
		uploadId = uploadId == null ? String.valueOf(System.currentTimeMillis()) : uploadId;
		log.info("The upload id is " + uploadId);
		String name = uploadId + "_0_" + ThreadLocalRandom.current().nextLong(1000000000, 9999999999l);
		HttpPost post = this.createUploadPostRequest(rUploadParams(uploadId, mediaType, isSideCar), name, this.createFileEntity());
		HttpResponse res = api.executeHttpRequest(post);
		
		return this.parseResult(res.getStatusLine().getStatusCode(), EntityUtils.toString(res.getEntity()));
	}

	@Override
	public InstagramUploadPhotoResult parseResult(int resultCode, String content) {
		return this.parseJson(resultCode, content, InstagramUploadPhotoResult.class);
	}

	private HttpPost createUploadPostRequest(String rparam, String name, HttpEntity entity) {
		HttpPost post = new HttpPost(InstagramConstants.BASE_API_URL + getUrl() + name);
		log.debug("Photo upload url: " + post.getURI());
		this.applyHeaders(post);
		post.addHeader("X-Instagram-Rupload-Params", rparam);
		post.addHeader("X_FB_WATERFALL_ID", UUID.randomUUID().toString());
		post.addHeader("Accept-Encoding", "gzip");
		post.addHeader("X-Entity-Name", name);
		post.addHeader("X-Entity-Type", "image/jpeg");
		post.addHeader("X-Entity-Length", String.valueOf(entity.getContentLength()));
		post.addHeader("Offset", "0");
		post.addHeader("Content-Type", "application/octet-stream");
		post.setEntity(entity);

		return post;
	}

	protected static String rUploadParams(String uploadId, String mediaType, boolean isSideCar) {
		return String.format(
				"{\"retry_context\":\"{\\\"num_step_auto_retry\\\":0,\\\"num_reupload\\\":0,\\\"num_step_manual_retry\\\":0}\",\"media_type\":\"%s\",\"upload_id\":\"%s\",\"xsharing_user_ids\":\"[]\",\" image_compression\":\"{\\\"lib_name\\\":\\\"moz\\\",\\\"lib_version\\\":\\\"3.1.m\\\",\\\"quality\\\":\\\"80\\\"}\"%s}",
				mediaType, uploadId, isSideCar ? ", \"is_sidecar\":\"1\"" : "");
	}

	private HttpEntity createFileEntity() throws IOException {
		return new ByteArrayEntity(Files.readAllBytes(file.toPath()));
	}
	
	@Getter
	@Setter
	public static class InstagramUploadPhotoResult extends StatusResult {
		private String upload_id;
	}

}
