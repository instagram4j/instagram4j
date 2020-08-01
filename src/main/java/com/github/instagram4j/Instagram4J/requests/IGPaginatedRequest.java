package com.github.instagram4j.Instagram4J.requests;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

public interface IGPaginatedRequest<T extends IGPaginatedResponse> {
    public void setMax_id(String s);
    public T execute(IGClient client) throws IGResponseException;
}
