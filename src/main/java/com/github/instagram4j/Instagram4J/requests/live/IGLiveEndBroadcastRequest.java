package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGLiveEndBroadcastRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String broadcastId;
    private boolean endAfterCopyrightWarning;
    
    @Override
    protected IGPayload getPayload() {
        return new IGPayload() {
            @Getter
            private boolean end_after_copyright_warning = endAfterCopyrightWarning;
        };
    }

    @Override
    public String path() {
        return "live/" + broadcastId + "/end_broadcast/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
