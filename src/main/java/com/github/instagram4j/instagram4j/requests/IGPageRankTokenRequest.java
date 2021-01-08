package com.github.instagram4j.instagram4j.requests;

import com.github.instagram4j.instagram4j.responses.IGPageRankTokenResponse;

public interface IGPageRankTokenRequest<T extends IGPageRankTokenResponse> {
    void setPage_token(String token);

    void setRank_token(String token);
}
