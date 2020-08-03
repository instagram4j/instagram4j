package com.github.instagram4j.Instagram4J.actions;

import java.lang.reflect.Field;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.actions.story.StoryAction;
import com.github.instagram4j.Instagram4J.actions.timeline.TimelineAction;
import com.github.instagram4j.Instagram4J.actions.upload.UploadAction;
import com.github.instagram4j.Instagram4J.actions.users.UsersAction;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

@Accessors(fluent = true, prefix = "_")
@Getter
public class IGClientActions {
    private UploadAction _upload;
    private TimelineAction _timeline;
    private StoryAction _story;
    private UsersAction _users;
    
    @SneakyThrows
    public IGClientActions(IGClient client) {
        for (Field field : this.getClass().getDeclaredFields())
            if (field.getName().startsWith("_"))
                field.set(this, field.getType().getConstructor(IGClient.class).newInstance(client));
    }
    
}
