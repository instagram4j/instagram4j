package com.github.instagram4j.Instagram4J.responses.users;

import com.github.instagram4j.Instagram4J.models.user.User;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class UserResponse extends IGResponse {
    private User user;
}
