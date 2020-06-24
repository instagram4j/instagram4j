package com.github.instagram4j.Instagram4J.responses;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.Data;

@Data
public class IGResponse {
	@JsonAnySetter
	private Map<String, Object> extra_properties = new HashMap<>();
    private String status;
    private String message;
    private boolean spam;
    private boolean lock;
    private String feedback_title;
    private String feedback_message;
    private String error_type;
    private String checkpoint_url;
    
    public Object getExtraProperty(String key) {
    	return extra_properties.get(key);
    }
}
