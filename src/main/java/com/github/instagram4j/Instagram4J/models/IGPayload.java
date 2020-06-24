package com.github.instagram4j.Instagram4J.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class IGPayload {
    @JsonIgnore
    protected Map<String, Object> extra_properties = new HashMap<>();
    private String _csrftoken;
    private String guid;
    private String device_id;
    private String phone_id;

    @JsonAnyGetter
    public Map<String, Object> getExtraProperties() {
        return extra_properties;
    }
    
    @JsonAnySetter
    public void addExtraProperty(String key, Object val) {
        extra_properties.put(key, val);
    }
}
