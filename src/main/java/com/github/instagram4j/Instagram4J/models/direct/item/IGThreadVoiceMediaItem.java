package com.github.instagram4j.Instagram4J.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.IGVoiceMedia;

import lombok.Data;

@Data
@JsonTypeName("voice_media")
public class IGThreadVoiceMediaItem extends IGThreadItem {
    private IGVoiceMedia media;
}
