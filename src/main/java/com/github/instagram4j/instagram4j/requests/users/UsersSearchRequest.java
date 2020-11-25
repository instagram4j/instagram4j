package com.github.instagram4j.instagram4j.requests.users;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.users.UsersSearchResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class UsersSearchRequest extends IGGetRequest<UsersSearchResponse> {
    @NonNull
    private String query;
    private int timezone_offset = 3600;
    private String rank_token;
    private String page_token;
    private final String search_surface = "user_search_page";
    private final int count = 30;

    public UsersSearchRequest(String _query, String _rank_token, String _page_token) {
        this.query = _query;
        this.rank_token = _rank_token;
        this.page_token = _page_token;
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("search_surface", search_surface, "timezone_offset",
                timezone_offset, "q", query, "count", count, "rank_token", rank_token, "page_token",
                page_token);
    }

    @Override
    public String path() {
        return "users/search/";
    }

    @Override
    public Class<UsersSearchResponse> getResponseType() {
        return UsersSearchResponse.class;
    }


}
