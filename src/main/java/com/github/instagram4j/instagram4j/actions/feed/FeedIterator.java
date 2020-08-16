package com.github.instagram4j.instagram4j.actions.feed;

import java.util.Iterator;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedIterator<T extends IGPaginatedResponse> implements Iterator<T> {
    @NonNull
    private IGClient client;
    @NonNull
    private IGPaginatedRequest<T> request;
    private T response;

    @Override
    public boolean hasNext() {
        return response == null || response.isMore_available();
    }

    @Override
    public T next() {
        response = request.execute(client).join();
        request.setMax_id(response.getNext_max_id());

        return response;
    }

}
