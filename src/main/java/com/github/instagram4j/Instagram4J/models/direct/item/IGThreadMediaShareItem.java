package com.github.instagram4j.Instagram4J.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.IGMedia;

import lombok.Data;

@Data
@JsonTypeName("media_share")
public class IGThreadMediaShareItem extends IGThreadItem {
    private IGMedia media;
}
