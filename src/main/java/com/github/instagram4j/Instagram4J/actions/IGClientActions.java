package com.github.instagram4j.Instagram4J.actions;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.actions.story.StoryAction;
import com.github.instagram4j.Instagram4J.actions.timeline.TimelineAction;
import com.github.instagram4j.Instagram4J.actions.upload.UploadAction;
import com.github.instagram4j.Instagram4J.actions.users.UsersAction;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class IGClientActions {
    @NonNull
    private IGClient client;
    private UploadAction upload;
    private TimelineAction timeline;
    private StoryAction story;
    private UsersAction users;
    
    public IGClientActions(IGClient client) {
        upload = new UploadAction(client);
        timeline = new TimelineAction(client);
        story = new StoryAction(client);
        users = new UsersAction(client);
    }
    
}
