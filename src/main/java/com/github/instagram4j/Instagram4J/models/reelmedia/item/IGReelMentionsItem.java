package com.github.instagram4j.Instagram4J.models.reelmedia.item;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class IGReelMentionsItem extends IGReelMetadataItem {
    @NonNull
    private String user_id;

    @Override
    public String key() {
        return "reel_mentions";
    }

}
