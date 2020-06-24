package com.github.instagram4j.Instagram4J.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Data;

@Data
public class IGPayload {
	private Map<String, String> extra_properties = new HashMap<>();
	private String _csrftoken;
	private String guid;
	private String device_id;
	private String phone_id;
	
	@JsonAnyGetter
	public Map<String, String> getExtraProperties() {
		return extra_properties;
	}
	
	public void addExtraProperty(String key, Object val) {
		extra_properties.put(key, IGUtils.objectToJson(val));
	}
}
