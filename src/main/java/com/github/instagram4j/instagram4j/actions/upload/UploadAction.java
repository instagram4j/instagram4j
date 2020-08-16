package com.github.instagram4j.instagram4j.actions.upload;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.requests.upload.MediaUploadFinishRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadPhotoRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadSegmentVideoGetRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadSegmentVideoPhaseRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadSegmentVideoPhaseRequest.Phase;
import com.github.instagram4j.instagram4j.requests.upload.RuploadVideoRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.media.RuploadPhotoResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UploadAction {
    @NonNull
    private IGClient client;

    public CompletableFuture<RuploadPhotoResponse> photo(byte[] data, String upload_id,
            boolean is_sidecar) {
        return new RuploadPhotoRequest(data, "1", upload_id, is_sidecar).execute(client);
    }

    public CompletableFuture<RuploadPhotoResponse> photo(byte[] data, String upload_id) {
        return photo(data, upload_id, false);
    }

    public CompletableFuture<? extends IGResponse> videoWithCover(byte[] data, byte[] cover,
            UploadParameters parameters) {
        return new RuploadVideoRequest(data, parameters)
                .execute(client)
                .thenCompose(response -> {
                    return photo(cover, parameters.getUpload_id());
                });
    }

    public CompletableFuture<? extends IGResponse> chunkedVideoWithCover(byte[] data, byte[] cover,
            int chunk_size, String upload_id) {
        return segments(upload_id, toSegments(data, chunk_size), data.length)
                .thenCompose(response -> {
                    return photo(cover, upload_id);
                });
    }

    public CompletableFuture<IGResponse> finish(String upload_id) {
        return new MediaUploadFinishRequest(upload_id).execute(client);
    }

    public CompletableFuture<IGResponse> segments(String upload_id, byte[][] segments,
            int totalLengthBytes) {
        String transfer_id = upload_id;
        UploadParameters parameter = UploadParameters.forIgtv(upload_id);
        return new RuploadSegmentVideoPhaseRequest(Phase.START, parameter)
                .execute(client)
                .thenApply(startResponse -> {
                    String stream_id = startResponse.get("stream_id").toString();

                    for (int i = 0; i < segments.length; i++) {
                        String offset = String.valueOf(i * segments[0].length);
                        RuploadSegmentVideoGetRequest getReq = new RuploadSegmentVideoGetRequest(
                                parameter, stream_id, transfer_id, offset);
                        String getoffset = getReq.execute(client).join().get("offset").toString();
                        log.debug("Offset : {}", getoffset);
                        RuploadSegmentVideoPhaseRequest transfer =
                                new RuploadSegmentVideoPhaseRequest(Phase.TRANSFER, parameter,
                                        stream_id, transfer_id, offset,
                                        String.valueOf(totalLengthBytes), segments[i]);
                        log.debug(String.format("Uploading %s (%s of %s)", transfer_id, i + 1,
                                segments.length));
                        transfer.execute(client).join();
                        log.debug(
                                String.format("Done with upload %s of %s", i + 1, segments.length));
                    }

                    return stream_id;
                })
                .thenCompose(stream_id -> {
                    return new RuploadSegmentVideoPhaseRequest(Phase.END, parameter, stream_id,
                            transfer_id).execute(client);
                });
    }

    public static byte[][] toSegments(byte[] data, int segmentSize) {
        int remainder = data.length % segmentSize;
        int segments = data.length / segmentSize + (remainder == 0 ? 0 : 1);
        byte[][] ans = new byte[segments][];
        for (int i = 0; i < (remainder == 0 ? segments : segments - 1); i++)
            ans[i] = Arrays.copyOfRange(data, i * segmentSize, i * segmentSize + segmentSize);
        if (remainder != 0)
            ans[ans.length - 1] = Arrays.copyOfRange(data, (ans.length - 1) * segmentSize,
                    (ans.length - 1) * segmentSize + remainder);

        return ans;
    }
}
