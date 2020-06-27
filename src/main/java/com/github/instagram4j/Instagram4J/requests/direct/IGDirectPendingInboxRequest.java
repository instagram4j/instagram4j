package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
public class IGDirectPendingInboxRequest extends IGGetRequest<IGResponse> {
    @NonNull
    private String cursor;
    
    @Override
    public String path() {
        return "/direct_v2/pending_inbox/";
    }
    
    @Override
    public String getQueryString() {
        return mapQueryString("cursor", cursor);
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
