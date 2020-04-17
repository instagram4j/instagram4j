/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.sun.istack.internal.NotNull;
import org.brunocvcunha.instagram4j.requests.internal.InstagramConfigurePhotoRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadResumablePhotoRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadResumablePhotoRequest.InstagramUploadPhotoResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureMediaResult;

import lombok.extern.log4j.Log4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramMediaTypeEnum;

import javax.imageio.ImageIO;

/**
 * Upload photo request
 *
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Log4j
public class InstagramUploadPhotoRequest extends InstagramRequest<InstagramConfigureMediaResult> {

	@NotNull
	private final BufferedImage bufferedImage;
	@NotNull
	private final String caption;
	private String uploadId;

	public InstagramUploadPhotoRequest(BufferedImage bufferedImage, String caption, String uploadId) {
		this.bufferedImage = bufferedImage;
		this.caption = caption;
		this.uploadId = uploadId;
	}

	public InstagramUploadPhotoRequest(BufferedImage bufferedImage, String caption) {
		this(bufferedImage, caption, null);
	}

	public InstagramUploadPhotoRequest(File file, String caption, String uploadId) throws IOException {
		this(ImageIO.read(file), caption, uploadId);
	}

	public InstagramUploadPhotoRequest(File file, String caption) throws IOException {
		this(ImageIO.read(file), caption, null);
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
	public InstagramConfigureMediaResult execute() throws IOException {
		InstagramUploadPhotoResult uploadResult = api.sendRequest(new InstagramUploadResumablePhotoRequest(bufferedImage, InstagramMediaTypeEnum.PHOTO, false));

		log.debug("Upload response code: " + uploadResult.getStatus());
		log.info("Upload photo result: " + uploadResult);
		if (!uploadResult.getStatus().equals("ok")) {
			log.error("Photo upload failed: " + uploadResult.getError_type() + " " + uploadResult.getMessage());
		}
		uploadId = uploadResult.getUpload_id();

		InstagramConfigureMediaResult configureResult = api.sendRequest(new InstagramConfigurePhotoRequest(bufferedImage, uploadId, caption));
		log.info("Configure photo result: " + configureResult);
		if (!configureResult.getStatus().equals("ok")) {
			log.error("Photo configure failed: " + configureResult.getError_type() + " " + configureResult.getMessage());
		}

		return configureResult;
	}

	@Override
	public InstagramConfigureMediaResult parseResult(int resultCode, String content) {
		log.info("Reading from: " + content);
		return parseJson(resultCode, content, InstagramConfigureMediaResult.class);
	}

}
