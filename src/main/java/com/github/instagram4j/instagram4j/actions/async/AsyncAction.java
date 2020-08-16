package com.github.instagram4j.instagram4j.actions.async;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGRequest;

public class AsyncAction {

    public static List<CompletableFuture<?>> executeRequestsAsync(IGClient client,
            IGRequest<?>... reqs) {
        return Stream.of(reqs).map(client::sendRequest).collect(Collectors.toList());
    }

}
