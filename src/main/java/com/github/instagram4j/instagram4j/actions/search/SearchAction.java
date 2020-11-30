package com.github.instagram4j.instagram4j.actions.search;

import java.util.concurrent.CompletableFuture;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.fbsearch.FbsearchPlacesRequest;
import com.github.instagram4j.instagram4j.requests.tags.TagsSearchRequest;
import com.github.instagram4j.instagram4j.responses.fbsearch.FbsearchPlacesResponse;
import com.github.instagram4j.instagram4j.responses.tags.TagsSearchResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SearchAction {
    @NonNull
    private IGClient client;

    public CompletableFuture<FbsearchPlacesResponse> searchPlace(String location, double lat,
            double lon) {
        return getFbsearchPlacesRequest(location, lat, lon, null, null).execute(client);
    }

    public CompletableFuture<TagsSearchResponse> searchTag(String tag, double lat, double lon) {
        return getSearchTagRequest(tag, lat, lon, null, null).execute(client);
    }

    public CompletableFuture<FbsearchPlacesResponse> searchPlace(String location, double lat,
            double lon,
            String page_token, String rank_token) {
        return getFbsearchPlacesRequest(location, lat, lon, page_token, rank_token).execute(client);
    }

    public CompletableFuture<TagsSearchResponse> searchTag(String location, double lat, double lon,
            String page_token, String rank_token) {
        return getSearchTagRequest(location, lat, lon, page_token, rank_token).execute(client);
    }

    private TagsSearchRequest getSearchTagRequest(String tag, double lat, double lon,
            String page_token, String rank_token) {
        return new TagsSearchRequest(tag, lat, lon, page_token, rank_token);
    }

    private FbsearchPlacesRequest getFbsearchPlacesRequest(String user, double lat, double lon,
            String page_token, String rank_token) {
        return new FbsearchPlacesRequest(user, lat, lon, page_token, rank_token);
    }

}
