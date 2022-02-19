package com.github.instagram4j.instagram4j.models.media.stories;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.instagram4j.models.media.ImageMedia;
import com.github.instagram4j.instagram4j.models.media.ImageVersions;

import lombok.Data;

@Data
@JsonTypeName("1")
public class StoriesImageMedia extends StoriesMedia implements ImageMedia {
    private ImageVersions image_versions2;
}
