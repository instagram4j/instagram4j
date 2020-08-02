package com.github.instagram4j.Instagram4J.actions.timeline;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.actions.feed.FeedIterator;
import com.github.instagram4j.Instagram4J.models.media.UploadParameters;
import com.github.instagram4j.Instagram4J.requests.feed.FeedTimelineRequest;
import com.github.instagram4j.Instagram4J.requests.media.MediaConfigureSidecarRequest;
import com.github.instagram4j.Instagram4J.requests.media.MediaConfigureSidecarRequest.MediaConfigureSidecarPayload;
import com.github.instagram4j.Instagram4J.requests.media.MediaConfigureSidecarRequest.SidecarChildrenMetadata;
import com.github.instagram4j.Instagram4J.requests.media.MediaConfigureTimelineRequest;
import com.github.instagram4j.Instagram4J.requests.media.MediaConfigureTimelineRequest.MediaConfigurePayload;
import com.github.instagram4j.Instagram4J.responses.feed.FeedTimelineResponse;
import com.github.instagram4j.Instagram4J.responses.media.MediaResponse.MediaConfigureSidecarResponse;
import com.github.instagram4j.Instagram4J.responses.media.MediaResponse.MediaConfigureTimelineResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class TimelineAction {
    @NonNull
    private IGClient client;
    
    public FeedIterator<FeedTimelineResponse> feed() {
        return new FeedIterator<>(client, new FeedTimelineRequest());
    }
    
    public MediaConfigureTimelineResponse uploadPhoto(byte[] data, MediaConfigurePayload payload) throws IOException {
        String upload_id = String.valueOf(System.currentTimeMillis());
        client.actions().upload().photo(data, upload_id);
        return new MediaConfigureTimelineRequest(payload.upload_id(upload_id)).execute(client);
    }
    
    public MediaConfigureTimelineResponse uploadPhoto(byte[] data, String caption) throws IOException {
        return uploadPhoto(data, new MediaConfigurePayload().caption(caption));
    }
    
    public MediaConfigureTimelineResponse uploadPhoto(File file, String caption) throws IOException {
        return uploadPhoto(Files.readAllBytes(file.toPath()), new MediaConfigurePayload().caption(caption));
    }
    
    public MediaConfigureTimelineResponse uploadPhoto(File file, MediaConfigurePayload payload) throws IOException, IOException {
        return uploadPhoto(Files.readAllBytes(file.toPath()), payload);
    }

    public MediaConfigureTimelineResponse uploadVideo(byte[] videoData, byte[] coverData, MediaConfigurePayload payload) throws IOException {
        String upload_id = String.valueOf(System.currentTimeMillis());
        client.actions().upload().video(videoData, coverData, UploadParameters.forTimelineVideo(upload_id, false));
        client.actions().upload().finish(upload_id);
        return new MediaConfigureTimelineRequest(payload.upload_id(upload_id)).execute(client);
    }
    
    public MediaConfigureTimelineResponse uploadVideo(File video, File cover, String caption) throws IOException, IOException {
        return uploadVideo(Files.readAllBytes(video.toPath()), Files.readAllBytes(cover.toPath()), new MediaConfigurePayload().caption(caption));
    }
    
    public MediaConfigureTimelineResponse uploadVideo(File video, File cover, MediaConfigurePayload payload) throws IOException, IOException {
        return uploadVideo(Files.readAllBytes(video.toPath()), Files.readAllBytes(cover.toPath()), payload);
    }
    
    public MediaConfigureTimelineResponse uploadVideo(byte[] videoData, byte[] coverData, String caption) throws IOException {
        return uploadVideo(videoData, coverData, new MediaConfigurePayload().caption(caption));
    }
    
    public MediaConfigureSidecarResponse uploadAlbum(List<SidecarInfo> infos, MediaConfigureSidecarPayload payload) throws IOException {
        for (SidecarInfo info : infos) info.upload(client);
        payload.children_metadata().addAll(infos.stream().map(SidecarInfo::metadata).collect(Collectors.toList()));
        return new MediaConfigureSidecarRequest(payload).execute(client);
    }
    
    public MediaConfigureSidecarResponse uploadAlbum(List<SidecarInfo> infos, String caption) throws IOException {
        return uploadAlbum(infos, new MediaConfigureSidecarPayload().caption(caption));
    }
    
    public static interface SidecarInfo {
         void upload(IGClient client) throws IOException;
         SidecarChildrenMetadata metadata();
    }
    
    @Data
    @Accessors(fluent = true, chain = true)
    @Setter
    public static class SidecarPhoto implements SidecarInfo {
        @NonNull
        private byte[] data;
        private SidecarChildrenMetadata metadata = new SidecarChildrenMetadata(String.valueOf(System.currentTimeMillis()));
        
        @Override
        public void upload(IGClient client) throws IOException {
            client.actions().upload().photo(data, metadata.upload_id(), true);
        }
        
        public static SidecarPhoto from(File file) throws IOException {
            return new SidecarPhoto(Files.readAllBytes(file.toPath()));
        }
        
        public static SidecarPhoto from(File file, SidecarChildrenMetadata metadata) throws IOException {
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
        private SidecarChildrenMetadata metadata = new SidecarChildrenMetadata(String.valueOf(System.currentTimeMillis()));
        
        @Override
        public void upload(IGClient client) throws IOException {
            client.actions().upload().video(data, cover_data, UploadParameters.forTimelineVideo(metadata.upload_id(), true));
        }
        
        public static SidecarVideo from(File video, File cover) throws IOException {
            return new SidecarVideo(Files.readAllBytes(video.toPath()), Files.readAllBytes(cover.toPath()));
        }
        
        public static SidecarVideo from(File video, File cover, SidecarChildrenMetadata metadata) throws IOException {
            return new SidecarVideo(Files.readAllBytes(video.toPath()), Files.readAllBytes(cover.toPath())).metadata(metadata);
        }
    }
}
