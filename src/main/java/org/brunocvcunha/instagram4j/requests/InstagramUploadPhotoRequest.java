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

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.requests.internal.InstagramConfigurePhotoRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadResumablePhotoRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadResumablePhotoRequest.InstagramUploadPhotoResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureMediaResult;

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
public class InstagramUploadPhotoRequest extends InstagramRequest<InstagramConfigureMediaResult> {
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
	public InstagramConfigureMediaResult execute() throws ClientProtocolException, IOException {
		InstagramUploadPhotoResult uploadResult = api.sendRequest(new InstagramUploadResumablePhotoRequest(file, "1"));
		log.debug("Upload response code: " + uploadResult.getStatus());
		log.info("Upload photo result: " + uploadResult);
		if(!uploadResult.getStatus().equals("ok")) { 
			log.error("Photo upload failed: " + uploadResult.getError_type() + " " + uploadResult.getMessage());
		}
		uploadId = uploadResult.getUpload_id();
		
		InstagramConfigureMediaResult configureResult = api.sendRequest(new InstagramConfigurePhotoRequest(file, uploadId, caption));
		log.info("Configure photo result: " + configureResult);
		if(!configureResult.getStatus().equals("ok")) {
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
