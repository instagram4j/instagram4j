package com.github.instagram4j.instagram4j.actions.feed;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGPageRankTokenRequest;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.responses.IGPageRankTokenResponse;
import com.github.instagram4j.instagram4j.responses.IGResponse;

public class PageRankTokenIterator<T extends IGRequest<R> & IGPageRankTokenRequest, R extends IGResponse & IGPageRankTokenResponse>
        extends CursorIterator<IGRequest<R>, R> {
    
    public PageRankTokenIterator(IGClient client, T t) {
        super(client, t, (req, id) -> IGPageRankTokenResponse.setFromFormat((IGPageRankTokenRequest) req, id), (res) -> res.toNextId(), (res) -> res.isHas_more());
    }
    
}
