package com.github.instagram4j.Instagram4J.responses.media;

import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGRuploadPhotoResponse extends IGResponse {
    private String upload_id;
}
