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

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.requests.internal.InstagramConfigureAlbumRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramConfigureAlbumRequest.AlbumChildrenMetadata;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadResumablePhotoRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadResumablePhotoRequest.InstagramUploadPhotoResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureAlbumResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureMediaResult;
import org.brunocvcunha.instagram4j.util.InstagramGenericUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * InstagramUploadAlbumRequest
 * 
 * @author Justin Vo
 *
 */

@RequiredArgsConstructor
@Log4j
public class InstagramUploadAlbumRequest extends InstagramPostRequest<InstagramConfigureAlbumResult> {
	@NonNull
	private Collection<File> imageFiles;
	@NonNull
	private String caption;

	@Override
	public String getUrl() {
		return "upload/photo/";
	}

	@Override
	public String getMethod() {
		return "POST";
	}

	@Override
	public InstagramConfigureAlbumResult execute() throws ClientProtocolException, IOException {
		List<AlbumChildrenMetadata> uploadIds = this.uploadPhotos();
		InstagramConfigureAlbumResult configurePhotoResult = api
				.sendRequest(new InstagramConfigureAlbumRequest(uploadIds, caption));

		if (!configurePhotoResult.getStatus().equalsIgnoreCase("ok")) {
			log.error("Failed to configure image: " + configurePhotoResult.getMessage());
		}

		return configurePhotoResult;

	}

	private List<AlbumChildrenMetadata> uploadPhotos() throws ClientProtocolException, IOException {
		List<AlbumChildrenMetadata> uploadIds = new ArrayList<>();
		long count = 0;
		for (File f : imageFiles) {
			String uploadId = String.valueOf(System.currentTimeMillis()) + String.valueOf(count++);
			if(count >= 10) {
				log.info("Only 10 items are allowed in album upload. Skipping the rest. . .");
				return uploadIds;
			}
			if (InstagramGenericUtil.isImageFile(f.toPath())) {
				InstagramUploadPhotoResult res = api
						.sendRequest(new InstagramUploadResumablePhotoRequest(f, "1", uploadId, true));
				if (!res.getStatus().equals("ok")) {
					log.error("Photo upload failed: " + res.getError_type() + " " + res.getMessage());
				} else {
					Dimension dimensions = InstagramGenericUtil.getImageDimension(f);
					uploadIds.add(AlbumChildrenMetadata.builder()
							.uploadId(res.getUpload_id())
							.height(dimensions.getHeight())
							.width(dimensions.getWidth())
							.build());
				}
			} else if (InstagramGenericUtil.isVideoFile(f.toPath())) {
				InstagramUploadVideoRequest req = InstagramUploadVideoRequest.builder().forAlbum(true).videoFile(f)
						.uploadId(uploadId).build();
				InstagramConfigureMediaResult res = api.sendRequest(req);
				if (!res.getStatus().equals("ok")) {
					log.error("Video upload failed: " + res.getError_type() + " " + res.getMessage());
				} else {
					uploadIds.add(AlbumChildrenMetadata.builder()
							.uploadId(res.getUpload_id())
							.height(Double.valueOf(req.getVidInfo()[1]))
							.width(Double.valueOf(req.getVidInfo()[2]))
							.isVideo(true)
							.duration(Integer.valueOf(req.getVidInfo()[0]))
							.build());
				}
			} else {
				log.info("MIME type indeterminate. Skipping. . . " + f.getAbsolutePath());
			}
		}

		return uploadIds;
	}

	@Override
	public InstagramConfigureAlbumResult parseResult(int resultCode, String content) {
		return null;
	}
}
