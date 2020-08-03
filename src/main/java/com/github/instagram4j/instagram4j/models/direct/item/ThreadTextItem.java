package com.github.instagram4j.instagram4j.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

@Data
@JsonTypeName("text")
public class ThreadTextItem extends ThreadItem {
    private String text;
}
