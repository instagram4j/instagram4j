package com.github.instagram4j.Instagram4J.responses.accounts;

import com.github.instagram4j.Instagram4J.models.user.IGUser;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGAccountsUserResponse extends IGResponse {
    private IGUser user;
}
