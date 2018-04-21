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
package org.brunocvcunha.instagram4j.requests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.internal.InstagramConfigureStoryRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramExposeRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureStoryResult;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.instagram4j.storymetadata.StoryMetadata;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * InstagramStoryPhotoUploadRequest
 * @author Justin Vo
 *
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Log4j
public class InstagramStoryPhotoUploadRequest extends InstagramPostRequest<InstagramConfigureStoryResult> {

    @NonNull
    private File imageFile;

    private List<StoryMetadata> metadata = null;

    @Override
    public String getUrl() {
        return "upload/photo/";
    }

    @Override
    public String getMethod() {
        return "POST";
    }
    
    @Override
    public InstagramConfigureStoryResult execute() throws ClientProtocolException, IOException {
        
        String uploadId = null;
        
        if (uploadId == null) {
            uploadId = String.valueOf(System.currentTimeMillis());
        }
        
        HttpPost post = createHttpRequest();
        post.setEntity(createMultipartEntity(uploadId));
        
        try (CloseableHttpResponse response = api.getClient().execute(post)) {
            api.setLastResponse(response);
            
            int resultCode = response.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(response.getEntity());
            
            log.info("Photo Upload result: " + resultCode + ", " + content);
            
            
            post.releaseConnection();
    
            StatusResult result = parseResult(resultCode, content);
            
            if (!result.getStatus().equalsIgnoreCase("ok")) {
                throw new RuntimeException("Error happened in photo upload: " + result.getMessage());
            }
            
            InstagramConfigureStoryResult configurePhotoResult = api.sendRequest(new InstagramConfigureStoryRequest(imageFile, uploadId, metadata));
            
            log.info("Configure photo result: " + configurePhotoResult);
            if (!configurePhotoResult.getStatus().equalsIgnoreCase("ok")) {
                throw new IllegalArgumentException("Failed to configure image: " + configurePhotoResult.getMessage());
            }
            
            StatusResult exposeResult = api.sendRequest(new InstagramExposeRequest());
            log.info("Expose result: " + exposeResult);
            if (!exposeResult.getStatus().equalsIgnoreCase("ok")) {
                throw new IllegalArgumentException("Failed to expose image: " + exposeResult.getMessage());
            }

            return configurePhotoResult;
        }
    }

    /**
     * Creates required multipart entity with the image binary
     * @return HttpEntity to send on the post
     * @throws ClientProtocolException
     * @throws IOException
     */
    protected HttpEntity createMultipartEntity(String uploadId) throws ClientProtocolException, IOException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("upload_id", uploadId);
        builder.addTextBody("_uuid", api.getUuid());
        builder.addTextBody("_csrftoken", api.getOrFetchCsrf());
        builder.addTextBody("image_compression", "{\"lib_name\":\"jt\",\"lib_version\":\"1.3.0\",\"quality\":\"87\"}");
        builder.addBinaryBody("photo", imageFile, ContentType.APPLICATION_OCTET_STREAM, "pending_media_" + uploadId + ".jpg");
        builder.setBoundary(api.getUuid());

        HttpEntity entity = builder.build();
        return entity;
    }

    /**
     * Creates the Post Request
     * @return Request
     */
    protected HttpPost createHttpRequest() {
        String url = InstagramConstants.API_URL + getUrl();
        log.info("URL Upload: " + url);

        HttpPost post = new HttpPost(url);
        post.addHeader("X-IG-Capabilities", "3Q4=");
        post.addHeader("X-IG-Connection-Type", "WIFI");
        post.addHeader("Cookie2", "$Version=1");
        post.addHeader("Accept-Language", "en-US");
        post.addHeader("Accept-Encoding", "gzip, deflate");
        post.addHeader("Connection", "close");
        post.addHeader("Content-Type", "multipart/form-data; boundary=" + api.getUuid());
        post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
        return post;
    }

    @Override
    public InstagramConfigureStoryResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramConfigureStoryResult.class);
    }
    
}
