package com.github.instagram4j.Instagram4J.requests.media;

import java.util.Arrays;
import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaConfigureResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

public class IGMediaConfigureRequest extends IGPostRequest<IGMediaConfigureResponse> {
	@NonNull
	private String uploadId, _caption;
	private Double imageWidth, imageHeight;
	@Setter
	private Long _length;
	
	public IGMediaConfigureRequest(String uploadId, String caption, double width, double height) {
		this.uploadId = uploadId;
		this._caption = caption;
		this.imageWidth = width;
		this.imageHeight = height;
	}
	
	public IGMediaConfigureRequest(String uploadId, String caption, long length) {
		this.uploadId = uploadId;
		this._caption = caption;
		this._length = length;
	}
	
	@Override
	protected IGPayload getPayload() {
		return _length == null ? new ImagePayload() : new VideoPayload();
	}

	@Override
	public String path() {
		return "/media/configure/" + (_length != null ? "?video=1" : "");
	}

	@Override
	public Class<IGMediaConfigureResponse> getResponseType() {
		return IGMediaConfigureResponse.class;
	}
	
	@Data
	protected class ImagePayload extends MediaConfigurePayload {
		private EditsMap edits = new EditsMap(Arrays.asList(imageWidth, imageHeight));
	}
	
	@Data
	protected class VideoPayload extends MediaConfigurePayload {
		private List<Clip> clips = Arrays.asList(new Clip(_length.toString()));
		private final double length = _length;
		private final int poster_frame_index = 0;
		private final boolean audio_muted = false;
	}
	
	@Data
	protected class MediaConfigurePayload extends IGPayload {
		private String upload_id = uploadId;
		private String caption = _caption;
		private String source_type = "4";
	}
	
	@Data
	public static class Clip {
		private final String length;
		private final String source_Type = "4";
	}
	
	@Data
	public static class EditsMap {
		private final List<Double> crop_original_size;
		private final List<Double> crop_center = Arrays.asList(0d, 0d);
		private final double crop_zoom = 1d;
	}
	
}