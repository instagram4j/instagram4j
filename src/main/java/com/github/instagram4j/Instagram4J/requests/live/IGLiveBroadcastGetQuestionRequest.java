package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGLiveBroadcastGetQuestionRequest extends IGGetRequest<IGResponse> {
    @NonNull
    private String broadcast_id;
    private int num_comments_requested = 5;
    private Long last_comment_ts;

    @Override
    public String path() {
        return "live/" + broadcast_id + "/get_question/";
    }
    
    @Override
    public String getQueryString() {
        return mapQueryString("num_comments_requested", String.valueOf(num_comments_requested), "last_comment_ts", String.valueOf(last_comment_ts));
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
