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
import java.util.Collection;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.requests.InstagramUploadResumablePhotoRequest.InstagramUploadPhotoResult;
import org.brunocvcunha.instagram4j.requests.internal.InstagramConfigureStoryRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureStoryResult;
import org.brunocvcunha.instagram4j.storymetadata.StoryMetadata;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * InstagramStoryPhotoUploadRequest
 * 
 * @author Justin Vo
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Log4j
public class InstagramUploadStoryPhotoRequest extends InstagramPostRequest<InstagramConfigureStoryResult> {
	@NonNull
	private File file;
	private Collection<StoryMetadata> metadata = null;
	private String threadId = null;

	public InstagramUploadStoryPhotoRequest(File img, Collection<StoryMetadata> meta) {
		this.file = img;
		this.metadata = meta;
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
	public InstagramConfigureStoryResult execute() throws ClientProtocolException, IOException {
		InstagramUploadPhotoResult uploadResult = api.sendRequest(new InstagramUploadResumablePhotoRequest(file, "1"));
		String uploadId = uploadResult.getUpload_id();
		InstagramConfigureStoryResult configureResult = api
				.sendRequest(new InstagramConfigureStoryRequest(file, uploadId, threadId, metadata));

		if (!configureResult.getStatus().equals("ok")) {
			log.error(
					"Photo configure failed: " + configureResult.getError_type() + " " + configureResult.getMessage());
		}
		return configureResult;
	}

	@Override
	public InstagramConfigureStoryResult parseResult(int resultCode, String content) {
		return null;
	}

}
