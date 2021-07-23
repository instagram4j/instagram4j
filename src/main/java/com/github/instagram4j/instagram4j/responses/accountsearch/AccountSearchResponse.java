package com.github.instagram4j.instagram4j.responses.accountsearch;

import java.util.List;

import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class AccountSearchResponse extends IGResponse {
	private int num_results;
	private List<Profile> users;
	
	private String rank_token;
    private String page_token;
    private boolean has_more;
}
