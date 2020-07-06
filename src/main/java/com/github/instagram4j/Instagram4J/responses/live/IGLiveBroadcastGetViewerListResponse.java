package com.github.instagram4j.Instagram4J.responses.live;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.user.IGProfile;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGLiveBroadcastGetViewerListResponse extends IGResponse {
    private List<IGProfile> users;
}
