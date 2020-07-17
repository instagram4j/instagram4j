package com.github.instagram4j.Instagram4J.models.discover;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
public class IGSectionalItem extends IGBaseModel {
    private String layout_type;
    private String feed_type;
}
