package com.github.instagram4j.instagram4j.responses;

public abstract class IGPaginatedResponse extends IGResponse {
    public abstract String getNext_max_id();
    public abstract boolean isMore_available();
}
