package com.github.instagram4j.Instagram4J.requests;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.IGConstants;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

@Slf4j
public abstract class IGPostRequest<T extends IGResponse> extends IGRequest<T> {

    protected abstract IGPayload getPayload();

    protected boolean isSigned() {
        return true;
    }

    @Override
    public Request formRequest(IGClient client) {
        Request.Builder req = new Request.Builder().url(IGConstants.BASE_API_URL + this.apiPath() + this.path());
        this.applyHeaders(client, req);
        req.post(this.getRequestBody(client));

        return req.build();
    }

    protected RequestBody getRequestBody(IGClient client) {
        String payload = IGUtils.objectToJson(client.setIGPayloadDefaults(getPayload()));
        log.debug("Payload : {}", payload);
        if (getPayload() == null) {
            return RequestBody.create("", null);
        }
        if (isSigned()) {
            return RequestBody.create(IGUtils.generateSignature(payload),
                    MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8"));
        } else {
            return RequestBody.create(payload, MediaType.parse("application/json; charset=UTF-8"));
        }
    }

}