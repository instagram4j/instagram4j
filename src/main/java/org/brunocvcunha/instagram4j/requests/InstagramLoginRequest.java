package org.brunocvcunha.instagram4j.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginPayload;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

/**
 * 
 * @author brunovolpato
 *
 */
@AllArgsConstructor
@Log4j
public class InstagramLoginRequest extends Instagram4jPostRequest {

    private InstagramLoginPayload payload;
    
    @Override
    @JsonIgnore
    public String getUrl() {
        return "accounts/login/";
    }

    @Override
    @JsonIgnore
    @SneakyThrows
    public String getPayload() {
        
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(payload);
        log.info("Payload: " + payloadJson);
        
        return payloadJson;
    }

    @Override
    public void onResponse(HttpResponse response) {
        
    }
}

