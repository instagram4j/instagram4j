package com.github.instagram4j.instagram4j.requests;

import java.util.concurrent.CompletableFuture;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;

public interface IGPaginatedRequest<T extends IGPaginatedResponse> {
    public void setMax_id(String s);

    public CompletableFuture<T> execute(IGClient client);
}
