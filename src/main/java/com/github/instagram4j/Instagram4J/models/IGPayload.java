package com.github.instagram4j.Instagram4J.models;

import lombok.Data;

@Data
public class IGPayload {
	private String _csrftoken;
	private String guid;
	private String device_id;
	private String phone_id;
}
