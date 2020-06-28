package com.github.instagram4j.Instagram4J.models.direct.item;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
public class IGThreadItem extends IGBaseModel {
    private String item_id;
    private String id = item_id;
    private long user_id;
    private long timestamp;
    private String item_type;
    private String text;
    private String client_context;
    private boolean show_forward_attribution;
}
