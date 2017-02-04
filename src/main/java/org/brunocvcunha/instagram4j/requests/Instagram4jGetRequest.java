package org.brunocvcunha.instagram4j.requests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.brunocvcunha.instagram4j.Instagram4jConstants;

/**
 * 
 * @author brunovolpato
 *
 */
public abstract class Instagram4jGetRequest extends Instagram4jRequest {

    @Override
    public String getMethod() {
        return "GET";
    }
    
    @Override
    public HttpResponse execute() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(Instagram4jConstants.API_URL + getUrl());
        get.addHeader("Connection", "close");
        get.addHeader("Accept", "*/*");
        get.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        get.addHeader("Cookie2", "$Version=1");
        get.addHeader("Accept-Language", "en-US");
        get.addHeader("User-Agent", Instagram4jConstants.USER_AGENT);
        
        return api.getClient().execute(get);
    }
    
}
