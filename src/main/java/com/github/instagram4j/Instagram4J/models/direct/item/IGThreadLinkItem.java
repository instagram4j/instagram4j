package com.github.instagram4j.Instagram4J.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.IGLink;

import lombok.Data;

@Data
@JsonTypeName("link")
public class IGThreadLinkItem extends IGThreadItem {
    private IGLink link;
}
