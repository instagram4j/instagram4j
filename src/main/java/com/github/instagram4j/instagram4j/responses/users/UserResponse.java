package com.github.instagram4j.instagram4j.responses.users;

import com.github.instagram4j.instagram4j.models.user.User;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class UserResponse extends IGResponse {
    private User user;
}
