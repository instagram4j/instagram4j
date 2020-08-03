package com.github.instagram4j.instagram4j.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.instagram4j.models.media.VoiceMedia;

import lombok.Data;

@Data
@JsonTypeName("voice_media")
public class ThreadVoiceMediaItem extends ThreadItem {
    private VoiceMedia media;
}
