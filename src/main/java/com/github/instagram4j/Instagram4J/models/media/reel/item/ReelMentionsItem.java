package com.github.instagram4j.Instagram4J.models.media.reel.item;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class ReelMentionsItem extends ReelMetadataItem {
    @NonNull
    private String user_id;

    @Override
    public String key() {
        return "reel_mentions";
    }

}
