package com.github.instagram4j.Instagram4J.models.reelmedia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public abstract class IGReelMetadataItem {
	private double x;
    private double y;
    private double z;
    private double width;
    private double height;
    private double rotation;
    private int is_pinned;
    private int is_hidden;
    private int is_sticker;
    public abstract String key();
}
