package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaGetStoryQuestionResponsesResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGMediaGetStoryQuestionResponsesRequest extends IGGetRequest<IGMediaGetStoryQuestionResponsesResponse> {
    @NonNull
    private String reel_id, question_id;
    private String max_id;
    
    @Override
    public String path() {
        return String.format("media/%s/%s/story_question_responses/", reel_id, question_id);
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id);
    }

    @Override
    public Class<IGMediaGetStoryQuestionResponsesResponse> getResponseType() {
        return IGMediaGetStoryQuestionResponsesResponse.class;
    }

}
