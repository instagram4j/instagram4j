package com.github.instagram4j.instagram4j.requests.accountsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.responses.accountsearch.AccountSearchResponse;

import lombok.Getter;

public class AccountSearch {

	private IGClient client;
	private String query;
	
	@Getter private int currentPageN;
	 
	private List<AccountSearchResponse> pages = new ArrayList<>();
	
	public AccountSearch(IGClient client, String query) {
		this.client = client;
		this.query = query;
		this.currentPageN = 0;
		
		search();
	}
	
	private void search() {
		int j = 0;
		while (true) {		
			if(j== 0) {
				AccountSearchResponse asr = null;				
				try {
					asr = new AccountSearchRequest(query).
							execute(client).get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}				
				pages.add(asr);
			}else {
				if(!pages.get(j-1).isHas_more()) 
					break;
				
				AccountSearchResponse asr = null;				
				try {
					asr = new AccountSearchTokenRequest(query, pages.get(j-1).getRank_token(), pages.get(j-1).getPage_token()).
							execute(client).get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				pages.add(asr);
			}
			
			j++;
		}	
		pages.remove(0);
	}
	
	public AccountSearchResponse getCurrentPage() {
		return pages.get(currentPageN);
	}
	
	public AccountSearchResponse getPageAt(int pos) {
		if(pos>=0 && pos < pages.size())
			return pages.get(pos);
		return null;
	}
	
	public void nextPage() {
		if(currentPageN < pages.size()-1)
			currentPageN++;
	}
	
	
	public void beforePage() {
		if(currentPageN > 0) 
			currentPageN--;
	}
	
	public int getTotalPages() {
		return pages.size();
	}
	
}
