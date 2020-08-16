package com.github.instagram4j.instagram4j.actions.timeline;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.feed.FeedIterable;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.requests.feed.FeedTimelineRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureSidecarRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureSidecarRequest.MediaConfigureSidecarPayload;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureSidecarRequest.SidecarChildrenMetadata;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureTimelineRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureTimelineRequest.MediaConfigurePayload;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.feed.FeedTimelineResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureSidecarResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureTimelineResponse;
import com.github.instagram4j.instagram4j.responses.media.RuploadPhotoResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class TimelineAction {
    @NonNull
    private IGClient client;

    public FeedIterable<FeedTimelineResponse> feed() {
        return new FeedIterable<>(client, FeedTimelineRequest::new);
    }

    public CompletableFuture<MediaConfigureTimelineResponse> uploadPhoto(byte[] data,
            MediaConfigurePayload payload) {
        return client.actions().upload()
                .photo(data, String.valueOf(System.currentTimeMillis()))
                .thenCompose(res -> new MediaConfigureTimelineRequest(payload.upload_id(res.getUpload_id())).execute(client));
    }

    public CompletableFuture<MediaConfigureTimelineResponse> uploadPhoto(byte[] data,
            String caption) {
        return uploadPhoto(data, new MediaConfigurePayload().caption(caption));
    }

    public CompletableFuture<MediaConfigureTimelineResponse> uploadPhoto(File file, String caption)
            throws IOException {
        return uploadPhoto(Files.readAllBytes(file.toPath()),
                new MediaConfigurePayload().caption(caption));
    }

    public CompletableFuture<MediaConfigureTimelineResponse> uploadPhoto(File file,
            MediaConfigurePayload payload) throws IOException {
        return uploadPhoto(Files.readAllBytes(file.toPath()), payload);
    }

    public CompletableFuture<MediaConfigureTimelineResponse> uploadVideo(byte[] videoData,
            byte[] coverData, MediaConfigurePayload payload) {
        String upload_id = String.valueOf(System.currentTimeMillis());
        return client.actions().upload()
                .videoWithCover(videoData, coverData,
                        UploadParameters.forTimelineVideo(upload_id, false))
                .thenCompose(response -> {
                    return client.actions().upload().finish(upload_id);
                })
                .thenCompose(response -> {
                    return new MediaConfigureTimelineRequest(payload.upload_id(upload_id))
                            .execute(client);
                });
    }

    public CompletableFuture<MediaConfigureTimelineResponse> uploadVideo(File video, File cover,
            String caption) throws IOException, IOException {
        return uploadVideo(Files.readAllBytes(video.toPath()), Files.readAllBytes(cover.toPath()),
                new MediaConfigurePayload().caption(caption));
    }

    public CompletableFuture<MediaConfigureTimelineResponse> uploadVideo(File video, File cover,
            MediaConfigurePayload payload) throws IOException, IOException {
        return uploadVideo(Files.readAllBytes(video.toPath()), Files.readAllBytes(cover.toPath()),
                payload);
    }

    public CompletableFuture<MediaConfigureTimelineResponse> uploadVideo(byte[] videoData,
            byte[] coverData, String caption) throws IOException {
        return uploadVideo(videoData, coverData, new MediaConfigurePayload().caption(caption));
    }

    public CompletableFuture<MediaConfigureSidecarResponse> uploadAlbum(List<SidecarInfo> infos,
            MediaConfigureSidecarPayload payload) {
        List<CompletableFuture<?>> uploadFutures =
                infos.stream().map(s -> s.upload(client)).collect(Collectors.toList());
        payload.children_metadata()
                .addAll(infos.stream().map(SidecarInfo::metadata).collect(Collectors.toList()));
        return CompletableFuture
                .allOf(uploadFutures.toArray(new CompletableFuture[uploadFutures.size()]))
                .thenCompose(v -> {
                    return new MediaConfigureSidecarRequest(payload).execute(client);
                });
    }

    public CompletableFuture<MediaConfigureSidecarResponse> uploadAlbum(List<SidecarInfo> infos,
            String caption) {
        return uploadAlbum(infos, new MediaConfigureSidecarPayload().caption(caption));
    }

    public static interface SidecarInfo {
        CompletableFuture<? extends IGResponse> upload(IGClient client);

        SidecarChildrenMetadata metadata();
    }

    @Data
    @Accessors(fluent = true, chain = true)
    @Setter
    public static class SidecarPhoto implements SidecarInfo {
        @NonNull
        private byte[] data;
        private SidecarChildrenMetadata metadata =
                new SidecarChildrenMetadata(String.valueOf(System.currentTimeMillis()));

        @Override
        public CompletableFuture<RuploadPhotoResponse> upload(IGClient client) {
            return client.actions().upload().photo(data, metadata.upload_id(), true);
        }

        public static SidecarPhoto from(File file) throws IOException {
            return new SidecarPhoto(Files.readAllBytes(file.toPath()));
        }

        public static SidecarPhoto from(File file, SidecarChildrenMetadata metadata)
                throws IOException {
            return new SidecarPhoto(Files.readAllBytes(file.toPath())).metadata(metadata);
        }
    }

    @Data
    @Accessors(fluent = true, chain = true)
    @Setter
    public static class SidecarVideo implements SidecarInfo {
        @NonNull
        private byte[] data;
        @NonNull
        private byte[] cover_data;
        private SidecarChildrenMetadata metadata =
                new SidecarChildrenMetadata(String.valueOf(System.currentTimeMillis()));

        @Override
        public CompletableFuture<? extends IGResponse> upload(IGClient client) {
            return client.actions().upload().videoWithCover(data, cover_data,
                    UploadParameters.forTimelineVideo(metadata.upload_id(), true));
        }

        public static SidecarVideo from(File video, File cover) throws IOException {
            return new SidecarVideo(Files.readAllBytes(video.toPath()),
                    Files.readAllBytes(cover.toPath()));
        }

        public static SidecarVideo from(File video, File cover, SidecarChildrenMetadata metadata)
                throws IOException {
            return new SidecarVideo(Files.readAllBytes(video.toPath()),
                    Files.readAllBytes(cover.toPath())).metadata(metadata);
        }
    }
}
