package com.github.instagram4j.instagram4j.responses;

public interface IGPaginatedResponse {
    public String getNext_max_id();

    public boolean isMore_available();
}
