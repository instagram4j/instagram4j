/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.inutils4j.MyStreamUtils;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Upload photo job request
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Log4j
@AllArgsConstructor
public class InstagramUploadVideoJobRequest extends InstagramRequest<StatusResult> {

    private String uploadId;
    private String uploadUrl;
    private String uploadJob;
    private File videoFile;
    
    
    @Override
    public String getUrl() {
        return uploadUrl;
    }

    @Override
    public String getMethod() {
        return "POST";
    }
    
    @Override
    public StatusResult execute() throws ClientProtocolException, IOException {
        
        String url = getUrl();
        log.info("URL Upload: " + url);
        
        HttpPost post = new HttpPost(url);
        post.addHeader("X-IG-Capabilities", "3Q4=");
        post.addHeader("X-IG-Connection-Type", "WIFI");
        post.addHeader("Cookie2", "$Version=1");
        post.addHeader("Accept-Language", "en-US");
        post.addHeader("Accept-Encoding", "gzip, deflate");
        post.addHeader("Content-Type", "application/octet-stream");
        post.addHeader("Session-ID", uploadId);
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Content-Disposition", "attachment; filename=\"video.mp4\"");
        post.addHeader("job", uploadJob);
        post.addHeader("Host", "upload.instagram.com");
        post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
        
        log.info("User-Agent: " + InstagramConstants.USER_AGENT);

        byte[] videoData = MyStreamUtils.readContentBytes(new FileInputStream(videoFile));
        
        //TODO: long ranges? need to handle?
        int requestSize = (int) Math.floor(videoData.length / 4.0);
        int lastRequestExtra = (int) (videoData.length - (requestSize * 3));
        
        
        for (int i = 0; i < 4; i++) {
            
            int start = i * requestSize;
            int end;
            if (i == 3) {
                end = i * requestSize + lastRequestExtra;
            } else {
                end = (i + 1) * requestSize;
            }
            
            int actualLength = (i == 3 ? lastRequestExtra : requestSize);
            
            String contentRange = String.format("bytes %s-%s/%s", start, end - 1, videoData.length);
            //post.setHeader("Content-Length", String.valueOf(end - start));
            post.setHeader("Content-Range", contentRange);
            
            byte[] range = Arrays.copyOfRange(videoData, start, start + actualLength);
            log.info("Total is " + videoData.length + ", sending " + actualLength + " (starting from " + start + ") -- " + range.length + " bytes.");
            
            post.setEntity(EntityBuilder.create().setBinary(range).build());

            try (CloseableHttpResponse response = api.getClient().execute(post)) {
                int resultCode = response.getStatusLine().getStatusCode();
                String content = EntityUtils.toString(response.getEntity());
                log.info("Result of part " + i + ": " + content);
                
                post.releaseConnection();
                response.close();
                
                if (resultCode != 200 && resultCode != 201) {
                    throw new IllegalStateException("Failed uploading video (" + resultCode + "): " + content);
                }
                
            }
            
        }
        
        return new StatusResult("ok");
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public StatusResult parseResult(int resultCode, String content) {
        return parseJson(content, StatusResult.class);
    }

}
