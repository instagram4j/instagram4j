package com.github.instagram4j.instagram4j.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class IGPayload extends IGBaseModel {
    private String _csrftoken;
    private String id;
    private String _uid;
    private String _uuid;
    private String guid;
    private String device_id;
    private String phone_id;
}
