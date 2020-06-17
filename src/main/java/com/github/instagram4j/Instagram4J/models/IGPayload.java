package com.github.instagram4j.Instagram4J.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IGPayload {
	private String _csrftoken;
	private String guid;
	private String device_id;
}
