package com.github.instagram4j.instagram4j.responses;

public interface IGPaginatedResponse {
    String getNext_max_id();

    boolean isMore_available();
}
