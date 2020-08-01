package com.github.instagram4j.Instagram4J.requests.archive;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

public class ArchiveReelRequest extends IGGetRequest<IGResponse> {

    @Override
    public String path() {
        return "archive/reel/day_shells/";
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("include_suggested_highlights", "false", 
                              "include_cover", "0", 
                              "is_in_archive_home", "false",
                              "timezone_offset", "0");
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
