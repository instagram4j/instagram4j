package com.github.instagram4j.Instagram4J.actions.feed;

import java.util.Iterator;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException;
import com.github.instagram4j.Instagram4J.requests.IGPaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

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
        try {
            response = request.execute(client);
            request.setMax_id(response.getNext_max_id());
            
            return response;
        } catch (IGResponseException exception) {
            throw new RuntimeException(exception);
        }
    }
    
}
