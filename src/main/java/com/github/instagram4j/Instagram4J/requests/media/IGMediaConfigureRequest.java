package com.github.instagram4j.Instagram4J.requests.media;

import java.util.Arrays;
import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
public class IGMediaConfigureRequest extends IGPostRequest<IGResponse> {
	@NonNull
	private String uploadId, _caption;
	private double imageWidth, imageHeight;

	@Override
	protected IGPayload getPayload() {
		return new IGPayload() {
			@Getter
			@Setter
			private String upload_id = uploadId;
			@Getter
			@Setter
			private String caption = _caption;
			@Getter
			@Setter
			private String media_folder = "Instagram";
			@Getter
			@Setter
			private String source_type = "4";
		};
	}

	@Override
	public String path() {
		return "/media/configure/";
	}

	@Override
	public Class<IGResponse> getResponseType() {
		return IGResponse.class;
	}
	
	@Data
	protected class MediaConfigurePayload extends IGPayload {
		private String upload_id = uploadId;
		private String caption = _caption;
		private String source_type = "4";
		private EditsMap edits = new EditsMap();
	}
	
	@Data
	protected class EditsMap {
		private final List<Double> crop_original_size = Arrays.asList(imageWidth, imageHeight);
		private final List<Double> crop_center = Arrays.asList(0d, 0d);
		private final double crop_zoom = 1d;
	}
	
}