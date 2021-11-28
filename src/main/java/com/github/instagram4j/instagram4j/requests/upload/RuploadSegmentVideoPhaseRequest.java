package com.github.instagram4j.instagram4j.requests.upload;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

@RequiredArgsConstructor
@AllArgsConstructor
public class RuploadSegmentVideoPhaseRequest extends IGPostRequest<IGResponse> {

    @NonNull
    private Phase phase;
    @NonNull
    private UploadParameters upload_params;
    private String stream_id;
    private String transfer_id, segment_offset, total_entity_length;
    private byte[] body;

    public RuploadSegmentVideoPhaseRequest(Phase phase, UploadParameters param, String stream_id,
            String transfer_id) {
        this(phase, param);
        this.transfer_id = transfer_id;
        this.stream_id = stream_id;
    }

    private final String uuid = IGUtils.randomUuid();

    @Override
    public String apiPath() {
        return "";
    }

    @Override
    public String path() {
        return "rupload_igvideo/" + (phase != Phase.START ? transfer_id : uuid);
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("segmented", "true", "phase", phase.name().toLowerCase(new Locale("en", "US")));
    }

    @Override
    public Request.Builder applyHeaders(IGClient client, Request.Builder req) {
        super.applyHeaders(client, req);
        req.addHeader("X-Instagram-Rupload-Params", IGUtils.objectToJson(upload_params));
        req.addHeader("X_FB_WATERFALL_ID", IGUtils.randomUuid());
        this.addHeadersBaseOnPhase(req);
        return req;
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

    @Override
    protected IGPayload getPayload(IGClient client) {
        return null;
    }

    @Override
    public RequestBody getRequestBody(IGClient client) {
        return phase == Phase.TRANSFER
                ? RequestBody.create(body, MediaType.get("application/octet-stream"))
                : super.getRequestBody(client);
    }

    public void addHeadersBaseOnPhase(Request.Builder req) {
        switch (phase) {
            default:
                return;
            case TRANSFER:
                phase.addToHeader(req,
                        stream_id,
                        "2",
                        segment_offset,
                        total_entity_length,
                        transfer_id,
                        "video/mp4",
                        segment_offset);
                break;
            case END:
                phase.addToHeader(req, stream_id);
                break;
        }
    }

    @Getter
    public static enum Phase {
        START, TRANSFER("Stream-Id",
                "Segment-Type",
                "Segment-Start-Offset",
                "X-Entity-Length",
                "X-Entity-Name",
                "X-Entity-Type",
                "Offset"), END("Stream-Id");

        private String[] headers;

        Phase(String... headers) {
            this.headers = headers;
        }

        public void addToHeader(Request.Builder req, String... values) {
            for (int i = 0; i < headers.length; i++)
                req.addHeader(headers[i], values[i]);
        }
    }

}
