package com.github.instagram4j.Instagram4J.actions.story;

import java.io.IOException;
import java.util.List;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.media.UploadParameters;
import com.github.instagram4j.Instagram4J.models.media.reel.item.ReelMetadataItem;
import com.github.instagram4j.Instagram4J.requests.feed.FeedReelsTrayRequest;
import com.github.instagram4j.Instagram4J.requests.feed.FeedUserStoryRequest;
import com.github.instagram4j.Instagram4J.requests.media.MediaConfigureToStoryRequest;
import com.github.instagram4j.Instagram4J.responses.feed.FeedReelsTrayResponse;
import com.github.instagram4j.Instagram4J.responses.feed.FeedUserStoryResponse;
import com.github.instagram4j.Instagram4J.responses.media.MediaResponse.MediaConfigureToStoryResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StoryAction {
    @NonNull
    private IGClient client;
    
    public MediaConfigureToStoryResponse uploadPhoto(byte[] data, List<ReelMetadataItem> metadata) throws IOException {
        String upload_id = String.valueOf(System.currentTimeMillis());
        client.actions().upload().photo(data, upload_id);
        
        return new MediaConfigureToStoryRequest(upload_id, metadata).execute(client);
    }
    
    public MediaConfigureToStoryResponse uploadVideo(byte[] video, byte[] cover, List<ReelMetadataItem> metadata) throws IOException {
        String upload_id = String.valueOf(System.currentTimeMillis());
        client.actions().upload().video(video, cover, UploadParameters.forAlbumVideo(upload_id));
        
        return new MediaConfigureToStoryRequest(upload_id, metadata).execute(client);
    }
    
    public FeedReelsTrayResponse tray() throws IOException {
        return new FeedReelsTrayRequest().execute(client);
    }
    
    public FeedUserStoryResponse userStory(long pk) throws IOException {
        return new FeedUserStoryRequest(pk).execute(client);
    }
    
}
