package com.github.instagram4j.Instagram4J.models.direct.item;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.models.user.IGProfile;

import lombok.Data;

@Data
@JsonTypeName("profile")
public class IGThreadProfileItem extends IGThreadItem {
    private IGProfile profile;
    private List<IGTimelineMedia> preview_medias;
}
