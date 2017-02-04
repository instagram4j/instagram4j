package org.brunocvcunha.instagram4j.requests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.brunocvcunha.instagram4j.Instagram4jConstants;
import org.brunocvcunha.instagram4j.util.Instagram4jHashUtil;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @author brunovolpato
 *
 */
@Log4j
public abstract class Instagram4jPostRequest extends Instagram4jRequest {

    @Override
    public String getMethod() {
        return "POST";
    }
    
    @Override
    public HttpResponse execute() throws ClientProtocolException, IOException {
        HttpPost post = new HttpPost(Instagram4jConstants.API_URL + getUrl());
        post.addHeader("Connection", "close");
        post.addHeader("Accept", "*/*");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.addHeader("Cookie2", "$Version=1");
        post.addHeader("Accept-Language", "en-US");
        post.addHeader("User-Agent", Instagram4jConstants.USER_AGENT);
        
        log.info("User-Agent: " + Instagram4jConstants.USER_AGENT);
        String parsedData = Instagram4jHashUtil.generateSignature(getPayload());
        log.info("Signed data: " + parsedData);
        post.setEntity(new StringEntity(parsedData));
        
        HttpResponse response = api.getClient().execute(post);
        
        return response;
    }

}
