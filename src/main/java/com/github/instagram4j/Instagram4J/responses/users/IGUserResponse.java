package com.github.instagram4j.Instagram4J.responses.users;

import com.github.instagram4j.Instagram4J.models.user.IGUser;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGUserResponse extends IGResponse {
    private IGUser user;
}
