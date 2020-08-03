package com.github.instagram4j.instagram4j.responses.media;

import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class RuploadPhotoResponse extends IGResponse {
    private String upload_id;
}
