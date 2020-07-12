package com.github.instagram4j.Instagram4J.models.igtv;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineVideoMedia;
import com.github.instagram4j.Instagram4J.models.user.IGUser;

import lombok.Data;

@Data
public class IGChannel extends IGBaseModel {
    private String id;
    private List<IGTimelineVideoMedia> items;
    private boolean more_available;
    private String title;
    private String type;
    private String max_id;
    private IGUser user_dict;
    private String description;
    private String cover_photo_url;
}
