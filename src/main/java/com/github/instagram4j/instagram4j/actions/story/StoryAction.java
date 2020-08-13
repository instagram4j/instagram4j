package com.github.instagram4j.instagram4j.actions.story;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.models.media.reel.item.ReelMetadataItem;
import com.github.instagram4j.instagram4j.requests.feed.FeedReelsTrayRequest;
import com.github.instagram4j.instagram4j.requests.feed.FeedUserStoryRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureToStoryRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedReelsTrayResponse;
import com.github.instagram4j.instagram4j.responses.feed.FeedUserStoryResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureToStoryResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StoryAction {
    @NonNull
    private IGClient client;
    
    public CompletableFuture<MediaConfigureToStoryResponse> uploadPhoto(byte[] data, List<ReelMetadataItem> metadata) {
        String upload_id = String.valueOf(System.currentTimeMillis());
        return client.actions().upload().photo(data, upload_id)
                .thenCompose(response -> {
                    return new MediaConfigureToStoryRequest(response.getUpload_id(), metadata).execute(client);
                });
    }
    
    public CompletableFuture<MediaConfigureToStoryResponse> uploadVideo(byte[] video, byte[] cover, List<ReelMetadataItem> metadata) {
        String upload_id = String.valueOf(System.currentTimeMillis());
        return client.actions().upload().videoWithCover(video, cover, UploadParameters.forAlbumVideo(upload_id))
                .thenCompose(Response -> {
                    return new MediaConfigureToStoryRequest(upload_id, metadata).execute(client);
                });
    }
    
    public CompletableFuture<FeedReelsTrayResponse> tray() {
        return new FeedReelsTrayRequest().execute(client);
    }
    
    public CompletableFuture<FeedUserStoryResponse> userStory(long pk) {
        return new FeedUserStoryRequest(pk).execute(client);
    }
    
}
