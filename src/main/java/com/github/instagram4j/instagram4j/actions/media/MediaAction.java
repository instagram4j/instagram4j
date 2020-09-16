package com.github.instagram4j.instagram4j.actions.media;

import java.util.concurrent.CompletableFuture;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.feed.FeedIterable;
import com.github.instagram4j.instagram4j.requests.media.MediaActionRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaCommentRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureSidecarRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureSidecarRequest.MediaConfigureSidecarPayload;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureTimelineRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureTimelineRequest.MediaConfigurePayload;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureToIgtvRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaEditRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaGetCommentsRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaInfoRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaGetCommentsResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaInfoResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureSidecarResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureTimelineResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureToIgtvResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MediaAction {
    @NonNull
    private IGClient client;
    @NonNull
    private String media_id;
    
    public CompletableFuture<IGResponse> comment(String comment) {
        return new MediaCommentRequest(media_id, comment).execute(client);
    }
    
    public CompletableFuture<MediaResponse> editCaption(String caption) {
        return new MediaEditRequest(media_id, caption).execute(client);
    }
    
    public CompletableFuture<MediaInfoResponse> info() {
        return new MediaInfoRequest(media_id).execute(client);
    }
    
    public FeedIterable<MediaGetCommentsResponse> comments() {
        return new FeedIterable<>(client, () -> new MediaGetCommentsRequest(media_id));
    }
    
    public CompletableFuture<IGResponse> action(MediaActionRequest.MediaAction action) {
        return new MediaActionRequest(media_id, action).execute(client);
    }
    
    public static MediaAction of(IGClient client, String media_id) {
        return new MediaAction(client, media_id);
    }
    
    public static CompletableFuture<MediaConfigureTimelineResponse> configureMediaToTimeline(IGClient client, String upload_id, MediaConfigurePayload payload) {
        return new MediaConfigureTimelineRequest(payload.upload_id(upload_id)).execute(client);
    }
    
    public static CompletableFuture<MediaConfigureSidecarResponse> configureAlbumToTimeline(IGClient client, MediaConfigureSidecarPayload payload) {
        return new MediaConfigureSidecarRequest(payload).execute(client);
    }
    
    public static CompletableFuture<MediaConfigureToIgtvResponse> configureToIgtv(IGClient client, String upload_id, String title, String caption, boolean postToFeed) {
        return new MediaConfigureToIgtvRequest(upload_id, title, caption, postToFeed).execute(client);
    }
}
