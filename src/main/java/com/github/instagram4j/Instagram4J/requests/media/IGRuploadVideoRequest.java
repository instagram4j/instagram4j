package com.github.instagram4j.Instagram4J.requests.media;

import java.util.concurrent.ThreadLocalRandom;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.models.IGUploadParameters;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGRuploadVideoRequest extends IGPostRequest<IGResponse> {
	@NonNull
	private byte[] videoData;
	@NonNull
	private String mediaType;
	@NonNull
	private String uploadId = String.valueOf(System.currentTimeMillis());
	private boolean is_sidecar = false;
	private boolean for_album = false;
	private final String name = uploadId + "_0_" + ThreadLocalRandom.current().nextLong(1_000_000_000, 9_999_999_999l);
	
	public IGRuploadVideoRequest(byte[] videoData, String mediaType, String uploadId) {
		this(videoData, mediaType, uploadId, false, false);
	}
	
	public IGRuploadVideoRequest(byte[] videoData, String mediaType, String uploadId, boolean is_sidecar) {
		this(videoData, mediaType, uploadId, is_sidecar, false);
	}

	@Override
	protected IGPayload getPayload() {
		return null;
	}

	@Override
	public String apiPath() {
		return "";
	}
	
	@Override
	public String path() {
		return "/rupload_igvideo/" + name;
	}
	
	@Override
	public Request.Builder applyHeaders(Request.Builder req) {
		super.applyHeaders(req);
		req.addHeader("X-Instagram-Rupload-Params", IGUploadParameters.forVideo(uploadId, mediaType, is_sidecar, for_album));
		req.addHeader("X_FB_VIDEO_WATERFALL_ID", IGUtils.randomUuid());
		req.addHeader("X-Entity-Type", "video/mp4");
		req.addHeader("Offset", "0");
		req.addHeader("X-Entity-Name", name);
		req.addHeader("X-Entity-Length", String.valueOf(videoData.length));
		
		return req;
	}
	
	@Override
	public RequestBody getRequestBody() {
		return RequestBody.create(videoData, MediaType.get("application/octet-stream"));
	}

	@Override
	public Class<IGResponse> getResponseType() {
		return IGResponse.class;
	}
	
}