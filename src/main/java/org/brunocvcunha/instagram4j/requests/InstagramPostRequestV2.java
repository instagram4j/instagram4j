package org.brunocvcunha.instagram4j.requests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.util.InstagramHashUtil;

import lombok.extern.log4j.Log4j;

public abstract class InstagramPostRequestV2<T> extends InstagramPostRequest<T> {

    // originally taken form
    // https://github.com/brunocvcunha/instagram4j/blob/master/src/main/java/org/brunocvcunha/instagram4j/requests/InstagramPostRequest.java
    // changed api version to v2, removed debug loggers
    @Override
    public T execute() throws ClientProtocolException, IOException {
        HttpPost post = new HttpPost("https://i.instagram.com/api/v2/" + getUrl());
        post.addHeader("Connection", "close");
        post.addHeader("Accept", "*/*");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.addHeader("Cookie2", "$Version=2");
        post.addHeader("Accept-Language", "en-US");
        post.addHeader("User-Agent", InstagramConstants.USER_AGENT);

        String payload = getPayload();

        if (isSigned()) {
            payload = InstagramHashUtil.generateSignature(payload);
        }
        post.setEntity(new StringEntity(payload));

        HttpResponse response = api.getClient().execute(post);
        api.setLastResponse(response);

        int resultCode = response.getStatusLine().getStatusCode();
        String content = EntityUtils.toString(response.getEntity());

        post.releaseConnection();

        return parseResult(resultCode, content);
    }

}
