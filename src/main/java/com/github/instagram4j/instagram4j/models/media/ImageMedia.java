package com.github.instagram4j.instagram4j.models.media;

import com.github.instagram4j.instagram4j.models.media.timeline.Comment.Caption;
import com.github.instagram4j.instagram4j.models.user.User;

public interface ImageMedia {
    long getPk();

    String getId();

    long getTaken_at();

    long getDevice_timestamp();

    String getMedia_type();

    String getCode();

    String getClient_cache_key();

    int getFilter_type();

    User getUser();

    Caption getCaption();

    ImageVersions getImage_versions2();

}
