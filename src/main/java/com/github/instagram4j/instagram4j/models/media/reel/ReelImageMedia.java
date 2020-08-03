package com.github.instagram4j.instagram4j.models.media.reel;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.instagram4j.models.media.ImageVersions;

import lombok.Data;

@Data
@JsonTypeName("1")
public class ReelImageMedia extends ReelMedia {
    private ImageVersions image_versions2;
}
