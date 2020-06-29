package com.github.instagram4j.Instagram4J.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

@Data
@JsonTypeName("text")
public class IGThreadTextItem extends IGThreadItem {
    private String text;
}
