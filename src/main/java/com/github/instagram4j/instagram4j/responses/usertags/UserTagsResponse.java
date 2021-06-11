package com.github.instagram4j.instagram4j.responses.usertags;

import java.util.List;

import com.github.instagram4j.instagram4j.models.media.Media;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class UserTagsResponse extends IGResponse {

	private List<Media> items;

}
