package com.github.instagram4j.Instagram4J.requests.media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.models.media.reel.item.ReelMetadataItem;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.media.MediaResponse.MediaConfigureToStoryResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class MediaConfigureToStoryRequest extends IGPostRequest<MediaConfigureToStoryResponse> {
    @NonNull
    private String uploadId;
    private List<ReelMetadataItem> story_metadata;
    private List<String> threadIds;

    public MediaConfigureToStoryRequest(String upload_id, List<ReelMetadataItem> story_metadata) {
        this.uploadId = upload_id;
        this.story_metadata = story_metadata;
    }

    @Override
    protected IGPayload getPayload(IGClient client) {
        return constructPayload();
    }

    @Override
    public String path() {
        return "media/configure_to_story/";
    }

    @Override
    public Class<MediaConfigureToStoryResponse> getResponseType() {
        return MediaConfigureToStoryResponse.class;
    }

    private MediaConfigureToStoryPayload constructPayload() {
        MediaConfigureToStoryPayload payload = new MediaConfigureToStoryPayload();
        if (story_metadata != null)
            addAllMetadata(payload, story_metadata);
        return payload;
    }

    public static void addAllMetadata(MediaConfigureToStoryPayload payload, List<ReelMetadataItem> items) {
        Map<String, List<ReelMetadataItem>> map = new HashMap<>();
        items.forEach(item -> {
            if (map.putIfAbsent(item.key(), new ArrayList<>(Arrays.asList(item))) != null)
                map.get(item.key()).add(item);
        });

        map.entrySet()
                .forEach(entry -> payload.put(entry.getKey(), IGUtils.objectToJson(entry.getValue())));
    }

    @Data
    @JsonInclude(Include.NON_NULL)
    public class MediaConfigureToStoryPayload extends IGPayload {
        private String upload_id = uploadId;
        private String source_type = "3";
        private String configure_mode = threadIds == null ? "1" : "2";
        private List<String> thread_ids = threadIds;
    }

}
