package com.github.instagram4j.Instagram4J.responses;

public abstract class IGPaginatedResponse extends IGResponse {
    public abstract String getNext_max_id();
    public abstract boolean isMore_available();
}
