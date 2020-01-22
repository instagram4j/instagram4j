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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.requests.internal.InstagramConfigureVideoRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadMediaFinishRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadResumablePhotoRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadResumableVideoRequest;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.inutils4j.MyImageUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Upload Video request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Log4j
@AllArgsConstructor
@RequiredArgsConstructor
public class InstagramUploadVideoRequest extends InstagramRequest<StatusResult> {
	@NonNull
	private File videoFile;
	@NonNull
	private String caption;
	private File thumbnailFile;

	@Override
	public String getUrl() {
		return "upload/video/";
	}

	@Override
	public String getMethod() {
		return "POST";
	}

	@Override
	public StatusResult execute() throws ClientProtocolException, IOException {
		String uploadId = String.valueOf(System.currentTimeMillis());
		String[] vidInfo = this.preparePreVideoProcessing();

		StatusResult uploadRes = api
				.sendRequest(new InstagramUploadResumableVideoRequest(videoFile, vidInfo, uploadId));
		if (!uploadRes.getStatus().equals("ok")) {
			log.error("An error has occured during video upload");
			return uploadRes;
		}

		StatusResult uploadThumbnailRes = api
				.sendRequest(new InstagramUploadResumablePhotoRequest(thumbnailFile, "1", uploadId, false));
		if(!uploadThumbnailRes.getStatus().equals("ok")) {
			log.error("An error has occured during thumbnail upload");
			return uploadThumbnailRes;
		}
		
		StatusResult finishRes = api.sendRequest(this.createFinishRequest(uploadId, vidInfo[0]));
		if(!finishRes.getStatus().equals("ok")) {
			log.error("An error has occured during finishing upload");
			return finishRes;
		}
		
		StatusResult configureResult = api.sendRequest(InstagramConfigureVideoRequest.builder().uploadId(uploadId)
				.caption(caption).duration(Long.valueOf(vidInfo[0])).build());
		
		return configureResult;
	}
	
	private InstagramUploadMediaFinishRequest createFinishRequest(String uploadId, String len) {
		List<Entry<String, Object>>	 params = new ArrayList<>();
		params.add(new SimpleEntry<>("length", len));
		List<Object> clips = Arrays.asList(new Object() {
			@Getter
			private String length = len;
			@Getter
			private String source_type = "4";
		});
		params.add(new SimpleEntry<>("clips", clips));
		params.add(new SimpleEntry<>("poster_frame_index", 0));
		params.add(new SimpleEntry<>("audio_muted", false));
		
		return new InstagramUploadMediaFinishRequest(uploadId, "4", params);
	}

	private String[] preparePreVideoProcessing() {
		String[] info = new String[3];

		try (FFmpegFrameGrabber fg = new FFmpegFrameGrabber(videoFile)) {
			fg.start();
			info[0] = String.valueOf(fg.getLengthInTime() / 1000);
			info[1] = String.valueOf(fg.getImageHeight());
			info[2] = String.valueOf(fg.getImageWidth());

			if (thumbnailFile == null) {
				Java2DFrameConverter converter = new Java2DFrameConverter();
				BufferedImage bufferedImage = MyImageUtils.deepCopy(converter.convert(fg.grabImage()));
				thumbnailFile = File.createTempFile("insta-" + System.currentTimeMillis(), ".jpg");

				log.info("Generated thumbnail: " + thumbnailFile.getAbsolutePath());
				ImageIO.write(bufferedImage, "JPG", thumbnailFile);
			}
		} catch (Exception e) {
			log.error("An exception has occured during video preprocessing: " + e.getMessage());
		} catch (IOException e) {
			log.error("An exception has occured during thumbnail creation: " + e.getMessage());
		}

		return info;
	}

	@Override
	public String getPayload() {
		return null;
	}

	@Override
	public StatusResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, StatusResult.class);
	}

}
