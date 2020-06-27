package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.direct.IGDirectInboxResponse;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
public class IGDirectInboxRequest extends IGGetRequest<IGDirectInboxResponse> {
    @NonNull
    private String cursor;
    
    @Override
    public String path() {
        return "/direct_v2/inbox/";
    }

    @Override
    public Class<IGDirectInboxResponse> getResponseType() {
        return IGDirectInboxResponse.class;
    }

}
