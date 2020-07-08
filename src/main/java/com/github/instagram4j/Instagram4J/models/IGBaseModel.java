package com.github.instagram4j.Instagram4J.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.Data;

@Data
public class IGBaseModel {
    @JsonAnySetter
    private Map<String, Object> $extra_properties = new HashMap<>();
    private String id;
    private Long pk;

    public Set<Entry<String, Object>> getExtraProperties() {
        return this.$extra_properties.entrySet();
    }

    public Object getExtraProperty(String key) {
        return $extra_properties.get(key);
    }
}