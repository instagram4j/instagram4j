package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
public class IGDirectInboxRequest extends IGGetRequest<IGResponse> {
    @NonNull
    private String cursor;
    
    @Override
    public String path() {
        return "/direct_v2/inbox/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
