package com.github.instagram4j.Instagram4J.models.discover;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
public class IGCluster extends IGBaseModel {
    private String id;
    private String title;
    private String context;
    private String description;
    private List<String> labels;
    private String name;
    private String type;
}
