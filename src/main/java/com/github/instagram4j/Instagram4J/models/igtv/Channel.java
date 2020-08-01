package com.github.instagram4j.Instagram4J.models.igtv;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.media.timeline.TimelineVideoMedia;
import com.github.instagram4j.Instagram4J.models.user.User;

import lombok.Data;

@Data
public class Channel extends IGBaseModel {
    private String id;
    private List<TimelineVideoMedia> items;
    private boolean more_available;
    private String title;
    private String type;
    private String max_id;
    private User user_dict;
    private String description;
    private String cover_photo_url;
}
