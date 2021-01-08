package com.github.instagram4j.instagram4j.actions.feed;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedIterable<T extends IGRequest<R> & IGPaginatedRequest, R extends IGResponse & IGPaginatedResponse> implements Iterable<R> {
    @NonNull
    private IGClient client;
    @NonNull
    private Supplier<T> requestSupplier;

    @Override
    public Iterator<R> iterator() {
        return new FeedIterator<T, R>(client, requestSupplier.get());
    }

    @Override
    public Spliterator<R> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.IMMUTABLE);
    }

    public Stream<R> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

}
