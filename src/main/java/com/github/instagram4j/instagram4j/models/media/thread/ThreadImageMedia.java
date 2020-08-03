package com.github.instagram4j.instagram4j.models.media.thread;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.instagram4j.models.media.ImageVersions;

import lombok.Data;

@Data
@JsonTypeName("1")
public class ThreadImageMedia extends ThreadMedia {
    private ImageVersions image_versions2;
}
