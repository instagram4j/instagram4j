package com.github.instagram4j.instagram4j.actions.clip;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.async.AsyncAction;
import com.github.instagram4j.instagram4j.actions.media.MediaAction;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineVideoMedia;
import com.github.instagram4j.instagram4j.requests.feed.FeedClipsRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureToClipsRequest.MediaConfigureToClipsPayload;
import com.github.instagram4j.instagram4j.responses.feed.FeedClipsResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureToClipsResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
public class ClipsAction {
    @NonNull
    private IGClient client;

    public CompletableFuture<MediaConfigureToClipsResponse> uploadClip(byte[] data, byte[] cover, MediaConfigureToClipsPayload payload) {
        String upload_id = String.valueOf(System.currentTimeMillis());

        return client.actions().upload()
                .videoWithCover(data, cover, UploadParameters.forClip(upload_id))
                .thenCompose(response -> client.actions().upload().finish(upload_id))
                .thenCompose(response -> MediaAction.configureToClips(client, upload_id, payload.length(Integer.toString(data.length))))
                .thenApply(CompletableFuture::completedFuture)
                .exceptionally(tr -> {
                    if (IGResponseException.IGFailedResponse.of(tr.getCause()).getStatusCode() != 202 &&
                            !(tr.getCause() instanceof SocketTimeoutException))
                        throw new CompletionException(tr.getCause());
                    log.info("{} Transcode not finished. Retrying up to three times.", upload_id);
                    return AsyncAction.retry(
                            () -> MediaAction.configureToClips(client, upload_id, payload),
                            tr, 3, 10,
                            TimeUnit.SECONDS);
                })
                .thenCompose(Function.identity());
    }

    public CompletableFuture<MediaConfigureToClipsResponse> uploadClip(File video, File cover,
                                                                       String caption) {
        try {
            return uploadClip(Files.readAllBytes(video.toPath()),
                    Files.readAllBytes(cover.toPath()),
                    new MediaConfigureToClipsPayload().caption(caption));
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    /**
     * Returns a response for feed clips request. Amount of clips usually = 5
     *
     * @param feedType type of feed clips:
     *                 {@link com.github.instagram4j.instagram4j.requests.feed.FeedClipsRequest.FeedType#DISCOVER} - provided by discovered authors;
     *                 {@link com.github.instagram4j.instagram4j.requests.feed.FeedClipsRequest.FeedType#FOLLOWING} - provided by authors you follow
     * @return the response for feed clips
     */
    public CompletableFuture<FeedClipsResponse> feedClips(FeedClipsRequest.FeedType feedType) {
        return new FeedClipsRequest(feedType).execute(client);
    }

    /**
     * Returns a list of feed found clips. The size of result list greater or equals than amount and almost equals.
     * If there are no more available clips then may return list with size less than amount. Method is not asynchronous
     *
     * @param amount   minimum of clips you need
     * @param feedType type of feed clips:
     *                 {@link com.github.instagram4j.instagram4j.requests.feed.FeedClipsRequest.FeedType#DISCOVER} - provided by discovered authors;
     *                 {@link com.github.instagram4j.instagram4j.requests.feed.FeedClipsRequest.FeedType#FOLLOWING} - provided by authors you follow
     * @return the list of {@link TimelineVideoMedia} clips
     */
    public List<TimelineVideoMedia> feedClipsList(FeedClipsRequest.FeedType feedType, int amount) {
        List<TimelineVideoMedia> res = new ArrayList<>();
        int counter = 0;
        String next_max_id = "";
        while (counter <= amount) {
            FeedClipsRequest.FeedClipsRequestPayload payload = new FeedClipsRequest.FeedClipsRequestPayload();
            payload.max_id(next_max_id);
            FeedClipsResponse tempResponse = new FeedClipsRequest(feedType, payload).execute(client).join();
            List<TimelineVideoMedia> tempList = tempResponse.getItems();
            res.addAll(tempList);
            if (!tempResponse.getPaging_info().getMore_available())
                break;
            next_max_id = tempResponse.getPaging_info().getMax_id();
            counter += tempList.size();
        }
        return res;
    }

    /**
     * Returns a list of feed found clips. The size of result list greater or equals than amount and almost equals.
     * Method is asynchronous. May serve clips with a high frequency of similarity
     *
     * @param amount   minimum of clips you need
     * @param feedType type of feed clips:
     *                 {@link com.github.instagram4j.instagram4j.requests.feed.FeedClipsRequest.FeedType#DISCOVER} - provided by discovered authors;
     *                 {@link com.github.instagram4j.instagram4j.requests.feed.FeedClipsRequest.FeedType#FOLLOWING} - provided by authors you follow
     * @return the list of {@link TimelineVideoMedia} clips
     */
    public CompletableFuture<List<TimelineVideoMedia>> feedClipsAsync(FeedClipsRequest.FeedType feedType, int amount) {
        int steps = (amount + 4) / 5;
        List<TimelineVideoMedia> list = new ArrayList<>();
        ArrayList<CompletableFuture<FeedClipsResponse>> futures = new ArrayList<>(steps);
        for (int i = 0; i < steps; i++)
            futures.add(new FeedClipsRequest(feedType).execute(client));
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).thenApply(unused -> {
            futures.forEach(future -> list.addAll(future.join().getItems()));
            return list;
        });
    }
}
