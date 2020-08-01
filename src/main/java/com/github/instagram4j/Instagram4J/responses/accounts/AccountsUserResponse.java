package com.github.instagram4j.Instagram4J.responses.accounts;

import com.github.instagram4j.Instagram4J.models.user.User;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class AccountsUserResponse extends IGResponse {
    private User user;
}
