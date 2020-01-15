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

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.InstagramResumablePhotoUploadRequest.InstagramPhotoUploadResult;
import org.brunocvcunha.instagram4j.requests.internal.InstagramConfigurePhotoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigurePhotoResult;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Upload photo request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Log4j
@RequiredArgsConstructor
public class InstagramUploadPhotoRequest extends InstagramRequest<InstagramConfigurePhotoResult> {
	@NonNull
	private File file;
	@NonNull
	private String caption;
	private String uploadId;
	
	public InstagramUploadPhotoRequest(File file, String caption, String uploadId) {
		this(file, caption);
		this.uploadId = uploadId;
	}
	
	@Override
	public String getUrl() {
		return "upload/photo/";
	}

	@Override
	public String getMethod() {
		return "post";
	}

	@Override
	public InstagramConfigurePhotoResult execute() throws ClientProtocolException, IOException {
		InstagramPhotoUploadResult uploadResult = api.sendRequest(new InstagramResumablePhotoUploadRequest(file, "1"));
		log.debug("Upload response code: " + uploadResult.getStatus());
		log.info("Upload photo result: " + uploadResult);
		if(!uploadResult.getStatus().equals("ok")) { 
			log.error("Photo upload failed: " + uploadResult.getError_type() + " " + uploadResult.getMessage());
		}
		uploadId = uploadResult.getUpload_id();
		
		InstagramConfigurePhotoResult configureResult = api.sendRequest(new InstagramConfigurePhotoRequest(ImageIO.read(file), uploadId, caption));
		log.info("Configure photo result: " + configureResult);
		if(!configureResult.getStatus().equals("ok")) {
			log.error("Photo configure failed: " + configureResult.getError_type() + " " + configureResult.getMessage());
		}
		
		return configureResult;
	}

	@Override
	public InstagramConfigurePhotoResult parseResult(int resultCode, String content) {
		log.info("Reading from: " + content);
		return parseJson(resultCode, content, InstagramConfigurePhotoResult.class);
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
 
	private String rUploadParams(String uploadId, String mediaType) {
		return String.format(
				"{\"retry_context\":{\"num_step_auto_retry\":0,\"num_reupload\":0,\"num_step_manual_retry\":0},\"media_type\":\"%s\",\"upload_id\":\"%s\",\"xsharing_user_ids\":[],\" image_compression\":{\"lib_name\":\"moz\",\"lib_version\":\"3.1.m\",\"quality\":\"80\"}}",
				mediaType, uploadId);
	}

	private HttpEntity createFileEntity() {
		return new FileEntity(file, ContentType.APPLICATION_OCTET_STREAM);
	}

}
