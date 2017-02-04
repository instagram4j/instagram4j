package org.brunocvcunha.instagram4j.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public abstract class Instagram4jRequest {

    @Getter @Setter
    @JsonIgnore
    protected Instagram4j api;
    
    /**
     * @return the url
     */
    public abstract String getUrl();
    
    /**
     * @return the method
     */
    public abstract String getMethod();
    
    /**
     * @return the payload
     */
    public abstract String getPayload();
    
    /**
     * @return the result
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public abstract HttpResponse execute() throws ClientProtocolException, IOException;
    
    /**
     * Process response
     * @param response On Response
     */
    public void onResponse(HttpResponse response) {
        // not mandatory
    }
}
