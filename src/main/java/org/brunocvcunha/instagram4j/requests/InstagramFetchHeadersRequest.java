package org.brunocvcunha.instagram4j.requests;

import org.brunocvcunha.instagram4j.util.Instagram4jGenericUtil;

/**
 * 
 * @author brunovolpato
 *
 */
public class InstagramFetchHeadersRequest extends Instagram4jGetRequest {

    @Override
    public String getUrl() {
        return "si/fetch_headers/?challenge_type=signup&guid=" + Instagram4jGenericUtil.generateUuid(false);
    }

    @Override
    public String getPayload() {
        return null;
    }

    
}
