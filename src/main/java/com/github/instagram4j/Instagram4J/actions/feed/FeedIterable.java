package com.github.instagram4j.Instagram4J.actions.feed;

import java.util.Iterator;
import java.util.function.Supplier;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGPaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedIterable<T extends IGPaginatedResponse> implements Iterable<T> {
    @NonNull
    private IGClient client;
    @NonNull
    private Supplier<IGPaginatedRequest<T>> supplier;
    
    @Override
    public Iterator<T> iterator() {
        return new FeedIterator<>(client, supplier.get());
    }
    
}
