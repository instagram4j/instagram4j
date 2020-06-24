package com.github.instagram4j.Instagram4J.requests;

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

    protected String getStringPayload() {
        return IGUtils.objectToJson(this.client.setIGLoad(getPayload()));
    }

    @Override
    public Request formRequest() {
        Request.Builder req = new Request.Builder().url(IGConstants.BASE_API_URL + this.apiPath() + this.path());
        this.applyHeaders(req);
        req.post(this.getRequestBody());

        return req.build();
    }

    protected RequestBody getRequestBody() {
        log.debug("Payload : {}", getStringPayload());
        if (getPayload() == null) {
            return RequestBody.create("", null);
        }
        if (isSigned()) {
            return RequestBody.create(IGUtils.generateSignature(getStringPayload()),
                    MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8"));
        } else {
            return RequestBody.create(getStringPayload(), MediaType.parse("application/json; charset=UTF-8"));
        }
    }

}