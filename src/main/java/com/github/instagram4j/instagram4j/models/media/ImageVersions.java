package com.github.instagram4j.instagram4j.models.media;

import java.util.List;

import lombok.Data;

@Data
public class ImageVersions {
    private List<ImageVersionsMeta> candidates;
}
