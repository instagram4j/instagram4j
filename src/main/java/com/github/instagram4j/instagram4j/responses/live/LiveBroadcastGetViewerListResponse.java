package com.github.instagram4j.instagram4j.responses.live;

import java.util.List;

import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class LiveBroadcastGetViewerListResponse extends IGResponse {
    private List<Profile> users;
}
