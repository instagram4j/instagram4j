package com.github.instagram4j.Instagram4J.actions.users;

import java.io.IOException;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.user.User;
import com.github.instagram4j.Instagram4J.requests.users.UsersInfoRequest;
import com.github.instagram4j.Instagram4J.requests.users.UsersUsernameInfoRequest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersAction {
    @NonNull
    private IGClient client;
    
    public UserAction findByUsername(String username) throws IOException {
        User user = new UsersUsernameInfoRequest(username).execute(client).getUser();
        
        return new UserAction(client, user);
    }
    
    public User info(long pk) throws IOException {
        return new UsersInfoRequest(pk).execute(client).getUser();
    }
}
