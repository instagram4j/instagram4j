package com.github.instagram4j.Instagram4J.models.reelmedia;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.IGImageVersions;

import lombok.Data;

@Data
@JsonTypeName("1")
public class IGReelImageMedia extends IGReelMedia {
    private IGImageVersions image_versions2;
}
