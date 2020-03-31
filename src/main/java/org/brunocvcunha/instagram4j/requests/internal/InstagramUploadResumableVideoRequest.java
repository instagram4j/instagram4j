package org.brunocvcunha.instagram4j.requests.internal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.instagram4j.util.InstagramGenericUtil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

@Log4j
@Builder
public class InstagramUploadResumableVideoRequest extends InstagramPostRequest<StatusResult> {
	@NonNull
	private File videoFile;
	@NonNull
	private String[] videoInfo;
	private String uploadId;
	@Builder.Default
	private boolean isSidecar = false;
	private final String uuid = InstagramGenericUtil.generateUuid(true);

	@Override
	public String getUrl() {
		return "rupload_igvideo/";
	}

	@Override
	public StatusResult execute() throws ClientProtocolException, IOException {
		uploadId = uploadId == null ? String.valueOf(System.currentTimeMillis()) : uploadId;
		String name = uploadId + "_0_" + ThreadLocalRandom.current().nextLong(1000000000, 9999999999l);
		String duration = videoInfo[0], height = videoInfo[1], width = videoInfo[2];
		String rupload = rUploadParams(uploadId, height, width, duration, isSidecar);
		HttpGet req = this.initUploadRequest(name, rupload);
		HttpResponse res = api.executeHttpRequest(req);
		InstagramInitVideoResult resVr = this.parseJson(res.getStatusLine().getStatusCode(),
				EntityUtils.toString(res.getEntity()), InstagramInitVideoResult.class);
		HttpPost postReq = this.createUploadPostRequest(name, rupload, String.valueOf(resVr.getOffset()),
				this.createFileEntity());
		HttpResponse postRes = api.executeHttpRequest(postReq);

		log.info(rupload);
		StatusResult resVu = this.parseResult(postRes.getStatusLine().getStatusCode(),
				EntityUtils.toString(postRes.getEntity()));

		return resVu;
	}

	@Override
	public StatusResult parseResult(int resultCode, String content) {
		log.info(resultCode + " Parsing: " + content);
		return this.parseJson(resultCode, content, StatusResult.class);
	}

	private HttpGet initUploadRequest(String name, String rupload) {
		HttpGet get = new HttpGet(InstagramConstants.BASE_API_URL + getUrl() + name);
		this.applyHeaders(get);
		get.setHeader("X-Instagram-Rupload-Params", rupload);
		get.setHeader("X_FB_VIDEO_WATERFALL_ID", uuid);
		get.setHeader("Accept-Encoding", "gzip");
		get.setHeader("X-Entity-Type", "video/mp4");

		return get;
	}

	private HttpPost createUploadPostRequest(String name, String rupload, String offset, HttpEntity entity) {
		HttpPost post = new HttpPost(InstagramConstants.BASE_API_URL + getUrl() + name);
		this.applyHeaders(post);

		post.setHeader("Accept-Encoding", "gzip");
		post.setHeader("X-Instagram-Rupload-Params", rupload);
		post.setHeader("X_FB_VIDEO_WATERFALL_ID", uuid);
		post.setHeader("X-Entity-Type", "video/mp4");
		post.setHeader("Offset", offset);
		post.setHeader("X-Entity-Name", name);
		post.setHeader("X-Entity-Length", String.valueOf(entity.getContentLength()));
		post.setHeader("Content-Type", "application/octet-stream");
		post.setEntity(entity);

		return post;
	}

	private String rUploadParams(String uploadId, String height, String width, String length, boolean isSidecar) {
		return String.format(
				"{\"retry_context\":\"{\\\"num_step_auto_retry\\\":0,\\\"num_reupload\\\":0,\\\"num_step_manual_retry\\\":0}\",\"media_type\":\"2\",\"xsharing_user_ids\":\"[]\",\"upload_id\":\"%s\",\"upload_media_height\":\"%s\",\"upload_media_width\":\"%s\",\"upload_media_duration_ms\":\"%s\"%s}",
				uploadId, height, width, length, isSidecar ? ",\"is_sidecar\": \"1\"" : "");
	}

	private HttpEntity createFileEntity() throws IOException {
		return new ByteArrayEntity(Files.readAllBytes(videoFile.toPath()));
	}

	@Getter
	protected static class InstagramInitVideoResult {
		private long offset;
	}

	public String[] getVideoInfo() {
		try (FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoFile)) {
			frameGrabber.start();

			return new String[] { String.valueOf(frameGrabber.getLengthInTime() / 1000l),
					String.valueOf(frameGrabber.getImageHeight()), String.valueOf(frameGrabber.getImageWidth()) };
		} catch (Exception e) {
			log.error("Exception occured when trying to grab video information: " + e.getMessage());
			return new String[] { "0", "0", "0" };
		}
	}

}
