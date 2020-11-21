package com.github.instagram4j.instagram4j.requests.users;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.users.UsersSearchResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersSearchRequest extends IGGetRequest<UsersSearchResponse> {

    private String _search_surface = "user_search_page";
    private int _timezone_offset = 3600;
    private String _query;
    private int _count = 30;

    public UsersSearchRequest(String _query) {
        this._query = _query;
    }

    public UsersSearchRequest(String _query, String _search_surface, int _timezone_offset, int _count) {
        this._query = _query;
        this._search_surface = _search_surface;
        this._timezone_offset = _timezone_offset;
        this._count = _count;
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("search_surface", _search_surface, "timezone_offset",
                _timezone_offset, "q", _query,"count",_count);
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
