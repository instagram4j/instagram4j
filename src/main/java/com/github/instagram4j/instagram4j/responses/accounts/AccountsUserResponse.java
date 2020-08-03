package com.github.instagram4j.instagram4j.responses.accounts;

import com.github.instagram4j.instagram4j.models.user.User;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class AccountsUserResponse extends IGResponse {
    private User user;
}
