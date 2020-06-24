package com.github.instagram4j.Instagram4J.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.Data;

@Data
public class IGPayload {
	private Map<Object, Object> extra_properties = new HashMap<>();
	private String _csrftoken;
	private String guid;
	private String device_id;
	private String phone_id;
	
	@JsonAnyGetter
	public Map<Object, Object> getExtraProperties() {
		return extra_properties;
	}
	
	@JsonAnySetter
	public void addExtraProperty(Object key, Object val) {
		extra_properties.put(key, val);
	}
}
