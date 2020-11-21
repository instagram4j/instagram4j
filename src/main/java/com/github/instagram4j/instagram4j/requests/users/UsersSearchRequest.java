package com.github.instagram4j.instagram4j.requests.users;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.users.UsersSearchResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersSearchRequest extends IGGetRequest<UsersSearchResponse> {

    private String search_surface = "user_search_page";
    private int timezone_offset = 3600;
    private String query;
    private int count = 30;
    private String rank_token;
    private String page_token;

    public UsersSearchRequest(String _query) {
        this.query = _query;
    }

    public UsersSearchRequest(String _query, String _rank_token, String _page_token) {
        this.query = _query;
        this.rank_token = _rank_token;
        this.page_token = _page_token;
    }

    public UsersSearchRequest(String _query, String _search_surface, int _timezone_offset, int _count) {
        this.query = _query;
        this.search_surface = _search_surface;
        this.timezone_offset = _timezone_offset;
        this.count = _count;
    }

    public UsersSearchRequest(String _query, String _search_surface, int _timezone_offset, int _count, String _rank_token, String _page_token) {
        this.search_surface = _search_surface;
        this.timezone_offset = _timezone_offset;
        this.query = _query;
        this.count = _count;
        this.rank_token = _rank_token;
        this.page_token = _page_token;
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("search_surface", search_surface, "timezone_offset",
                timezone_offset, "q", query,"count", count,"rank_token",rank_token,"page_token",page_token);
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
