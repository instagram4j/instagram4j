package com.github.instagram4j.Instagram4J.models.creatives;

import java.util.List;

import lombok.Data;

@Data
public class IGStaticSticker {
	private String id;
	private List<IGSticker> stickers;
	private List<String> keywords;
	
	@Data
	public static class IGSticker {
	    private String id;
	    private String type;
	    private String name;
	    private String image_url;
	    private double image_width_ratio;
	    private double tray_image_width_ratio;
	    private double image_width;
	    private double image_height;
	}
}