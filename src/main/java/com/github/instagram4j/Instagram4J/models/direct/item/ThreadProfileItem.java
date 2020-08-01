package com.github.instagram4j.Instagram4J.models.direct.item;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.timeline.TimelineMedia;
import com.github.instagram4j.Instagram4J.models.user.Profile;

import lombok.Data;

@Data
@JsonTypeName("profile")
public class ThreadProfileItem extends ThreadItem {
    private Profile profile;
    private List<TimelineMedia> preview_medias;
}
