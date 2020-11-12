package com.github.instagram4j.instagram4j.actions.feed;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;

public class FeedIterator<T extends IGRequest<R> & IGPaginatedRequest<R>, R extends IGPaginatedResponse> extends CursorIterator<IGRequest<R>, R> {
    
    public FeedIterator(IGClient client, T t) {
        super(client, t, (o, s) -> ((IGPaginatedRequest<?>) o).setMax_id(s), IGPaginatedResponse::getNext_max_id, IGPaginatedResponse::isMore_available);
    }

}
