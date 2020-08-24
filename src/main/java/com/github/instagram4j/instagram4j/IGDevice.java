package com.github.instagram4j.instagram4j;

import java.io.Serializable;
import java.util.Map;
import lombok.Data;

@Data
public class IGDevice implements Serializable {
    private static final long serialVersionUID = -823447845648l;
    private final String userAgent;
    private final String capabilities;
    private final Map<String, Object> deviceMap;
}
