package com.github.instagram4j.instagram4j.actions.clip;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.async.AsyncAction;
import com.github.instagram4j.instagram4j.actions.media.MediaAction;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureToClipsRequest.MediaConfigureToClipsPayload;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureToClipsResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
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
}
