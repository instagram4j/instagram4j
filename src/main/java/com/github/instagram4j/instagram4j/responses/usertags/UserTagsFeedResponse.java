package com.github.instagram4j.instagram4j.responses.usertags;

import java.util.List;

import com.github.instagram4j.instagram4j.models.media.Media;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class UserTagsFeedResponse extends IGResponse implements IGPaginatedResponse{

	private List<Media> items;
	
	private int num_results;
	private String next_max_id;
	private boolean more_available;
	
	
}
