package com.github.instagram4j.instagram4j.actions;

import java.lang.reflect.Field;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.account.AccountAction;
import com.github.instagram4j.instagram4j.actions.clip.ClipsAction;
import com.github.instagram4j.instagram4j.actions.igtv.IgtvAction;
import com.github.instagram4j.instagram4j.actions.search.SearchAction;
import com.github.instagram4j.instagram4j.actions.simulate.SimulateAction;
import com.github.instagram4j.instagram4j.actions.status.StatusAction;
import com.github.instagram4j.instagram4j.actions.story.StoryAction;
import com.github.instagram4j.instagram4j.actions.timeline.TimelineAction;
import com.github.instagram4j.instagram4j.actions.upload.UploadAction;
import com.github.instagram4j.instagram4j.actions.users.UsersAction;
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
    private SimulateAction _simulate;
    private IgtvAction _igtv;
    private ClipsAction _clips;
    private AccountAction _account;
    private SearchAction _search;
    private StatusAction _status;

    @SneakyThrows
    public IGClientActions(IGClient client) {
        for (Field field : this.getClass().getDeclaredFields())
            if (field.getName().startsWith("_"))
                field.set(this, field.getType().getConstructor(IGClient.class).newInstance(client));
    }

}
