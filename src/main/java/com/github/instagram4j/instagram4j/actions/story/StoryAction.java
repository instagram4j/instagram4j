package com.github.instagram4j.instagram4j.actions.story;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.models.media.stories.item.StoriesMetadataItem;
import com.github.instagram4j.instagram4j.requests.feed.FeedStoriesTrayRequest;
import com.github.instagram4j.instagram4j.requests.feed.FeedUserStoryRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureToStoryRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedStoriesTrayResponse;
import com.github.instagram4j.instagram4j.responses.feed.FeedUserStoryResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureToStoryResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StoryAction {
    @NonNull
    private IGClient client;

    public CompletableFuture<MediaConfigureToStoryResponse> uploadPhoto(byte[] data,
            List<StoriesMetadataItem> metadata) {
        String upload_id = String.valueOf(System.currentTimeMillis());
        return client.actions().upload()
                .photo(data, upload_id)
                .thenCompose(response -> {
                    return new MediaConfigureToStoryRequest(response.getUpload_id(), metadata)
                            .execute(client);
                });
    }

    public CompletableFuture<MediaConfigureToStoryResponse> uploadVideo(byte[] video, byte[] cover,
            List<StoriesMetadataItem> metadata) {
        String upload_id = String.valueOf(System.currentTimeMillis());
        return client.actions().upload()
                .videoWithCover(video, cover, UploadParameters.forAlbumVideo(upload_id))
                .thenCompose(Response -> {
                    return new MediaConfigureToStoryRequest(upload_id, metadata).execute(client);
                });
    }
    
    public CompletableFuture<MediaConfigureToStoryResponse> uploadPhoto(File file, List<StoriesMetadataItem> metadata) {
        try {
            return uploadPhoto(Files.readAllBytes(file.toPath()), metadata);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public CompletableFuture<MediaConfigureToStoryResponse> uploadPhoto(File file) {
        return uploadPhoto(file, Collections.emptyList());
    }
    
    public CompletableFuture<MediaConfigureToStoryResponse> uploadVideo(File videoFile, File coverFile, List<StoriesMetadataItem> metadata) {
        try {
            return uploadVideo(Files.readAllBytes(videoFile.toPath()), Files.readAllBytes(coverFile.toPath()), metadata);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public CompletableFuture<MediaConfigureToStoryResponse> uploadVideo(File videoFile, File coverFile) {
        return uploadVideo(videoFile, coverFile, Collections.emptyList());
    }

    public CompletableFuture<FeedStoriesTrayResponse> tray() {
        return new FeedStoriesTrayRequest().execute(client);
    }

    public CompletableFuture<FeedUserStoryResponse> userStory(long pk) {
        return new FeedUserStoryRequest(pk).execute(client);
    }

}
