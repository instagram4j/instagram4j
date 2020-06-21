package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
public class IGMediaUploadFinishRequest extends IGPostRequest<IGResponse> {
	@NonNull
	private String uploadId;
	@NonNull
	private String sourceType;
	private IGMediaUploadFinishPayload payload = new IGMediaUploadFinishPayload();
	
	@Override
	protected IGPayload getPayload() {
		payload.setUpload_id(uploadId);
		payload.setSource_type(sourceType);
		return payload;
	}

	@Override
	public String path() {
		return "/media/upload_finish/?video=1";
	}

	@Override
	public Class<IGResponse> getResponseType() {
		return IGResponse.class;
	}
	
	@Data
	public static class IGMediaUploadFinishPayload extends IGPayload {
		private String upload_id;
		private String source_type;
	}
}