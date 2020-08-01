package com.github.instagram4j.Instagram4J.models.media;

import java.util.List;

import lombok.Data;

@Data
public class ImageVersions {
    private List<ImageVersionsMeta> candidates;
}
