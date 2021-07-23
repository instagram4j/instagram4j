package com.github.instagram4j.instagram4j.requests.accountsearch;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.accountsearch.AccountSearchResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;


@AllArgsConstructor
public class AccountSearchTokenRequest extends IGGetRequest<AccountSearchResponse> {

	@NonNull
	private String q;
	@NonNull
	private String rank_token;	
	@NonNull
	private String page_token;
	
	@Override
    public String path() {       
        return "users/search/";
    }
	
	@Override
    public String getQueryString(IGClient client) {      
        return mapQueryString("search_surface", "user_search_page", 
        					  "timezone_offset", "7200",
        					  "q", q,
        					  "count", "30",
        					  "rank_token", rank_token,
        					  "page_token", page_token);
    }
		
	@Override
	public Class<AccountSearchResponse> getResponseType() {
		return AccountSearchResponse.class;
	}
}
