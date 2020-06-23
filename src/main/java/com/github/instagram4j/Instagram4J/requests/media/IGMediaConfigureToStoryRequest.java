package com.github.instagram4j.Instagram4J.requests.media;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.requests.media.IGMediaConfigureRequest.EditsMap;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaConfigureResponse.IGMediaConfigureToStoryResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGMediaConfigureToStoryRequest extends IGPostRequest<IGMediaConfigureToStoryResponse> {
	@NonNull
	private String uploadId;
	@NonNull
	private Double imageWidth, imageHeight;
	private List<String> threadIds;
	
	@Override
	protected IGPayload getPayload() {
		return new IGMediaConfigureToStoryPayload();
	}

	@Override
	public String path() {
		return "/media/configure_to_story/";
	}

	@Override
	public Class<IGMediaConfigureToStoryResponse> getResponseType() {
		return IGMediaConfigureToStoryResponse.class;
	}
	
	@Data
	@JsonInclude(Include.NON_NULL)
	public class IGMediaConfigureToStoryPayload extends IGPayload {
		private String upload_id = uploadId;
		private String source_type = "3";
		private String configure_mode = "1";
		private List<String> thread_ids = threadIds;
	}

}
