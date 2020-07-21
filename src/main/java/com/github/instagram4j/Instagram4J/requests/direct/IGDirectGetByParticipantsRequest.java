package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.direct.IGDirectThreadsResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

public class IGDirectGetByParticipantsRequest extends IGGetRequest<IGDirectThreadsResponse> {
    private Long[] _participants;
    
    public IGDirectGetByParticipantsRequest(Long... participants) {
        this._participants = participants;
    }
    
    @Override
    public String path() {
        return "direct_v2/threads/get_by_participants/";
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("recipient_users", IGUtils.objectToJson(_participants));
    }

    @Override
    public Class<IGDirectThreadsResponse> getResponseType() {
        return IGDirectThreadsResponse.class;
    }

}
