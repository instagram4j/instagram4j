package com.github.instagram4j.instagram4j.actions.users;

import java.util.concurrent.CompletableFuture;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.user.User;
import com.github.instagram4j.instagram4j.requests.users.UsersInfoRequest;
import com.github.instagram4j.instagram4j.requests.users.UsersSearchRequest;
import com.github.instagram4j.instagram4j.requests.users.UsersUsernameInfoRequest;
import com.github.instagram4j.instagram4j.responses.users.UserResponse;
import com.github.instagram4j.instagram4j.responses.users.UsersSearchResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersAction {
    @NonNull
    private IGClient client;

    public CompletableFuture<UserAction> findByUsername(String username) {
        return new UsersUsernameInfoRequest(username).execute(client)
                .thenApply(response -> {
                    return new UserAction(client, response.getUser());
                });
    }

    public CompletableFuture<User> info(long pk) {
        return new UsersInfoRequest(pk).execute(client)
                .thenApply(UserResponse::getUser);
    }

    public CompletableFuture<UsersSearchResponse> search(String query) {
        return new UsersSearchRequest(query).execute(client);
    }

    public CompletableFuture<UsersSearchResponse> search(String query, String search_surface, int timezone_offset, int count) {
        return new UsersSearchRequest(query, search_surface, timezone_offset, count).execute(client);
    }

    public CompletableFuture<UsersSearchResponse> getSearchNextPage(UsersSearchResponse usersSearchResponse, String query) {
        return new UsersSearchRequest(query,usersSearchResponse.getRank_token(),usersSearchResponse.getPage_token()).execute(client);
    }

    public CompletableFuture<UsersSearchResponse> getSearchNextPage(UsersSearchResponse usersSearchResponse, String query, String search_surface, int timezone_offset, int count) {
        return new UsersSearchRequest(query, search_surface, timezone_offset, count,usersSearchResponse.getRank_token(),usersSearchResponse.getPage_token()).execute(client);
    }

}
