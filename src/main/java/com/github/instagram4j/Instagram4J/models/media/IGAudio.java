package com.github.instagram4j.Instagram4J.models.media;

import lombok.Data;

@Data
public class IGAudio {
    private String audio_src;
    private long duration;
    private double[] waveform_data;
    private int waveform_sampling_frequency_hz;
}
