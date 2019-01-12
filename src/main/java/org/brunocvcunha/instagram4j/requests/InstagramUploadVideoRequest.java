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

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.internal.InstagramConfigureVideoRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramExposeRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramUploadVideoJobRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUploadVideoResult;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.inutils4j.MyImageUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Upload Video request
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Log4j
@AllArgsConstructor
@RequiredArgsConstructor
public class InstagramUploadVideoRequest extends InstagramRequest<StatusResult> {

    @NonNull
    private File videoFile;
    @NonNull
    private String caption;
    private File thumbnailFile;
    
    @Override
    public String getUrl() {
        return "upload/video/";
    }

    @Override
    public String getMethod() {
        return "POST";
    }
    
    @Override
    public StatusResult execute() throws ClientProtocolException, IOException {
        
        HttpPost post = createHttpRequest();
        
        String uploadId = String.valueOf(System.currentTimeMillis());
        
        post.setEntity(createMultipartEntity(uploadId));
        
        try (CloseableHttpResponse response = api.getClient().execute(post)) {
            api.setLastResponse(response);
            
            int resultCode = response.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(response.getEntity());
            log.info("First phase result " + resultCode + ": " + content);
            
            post.releaseConnection();
            
            InstagramUploadVideoResult firstPhaseResult = parseJson(content, InstagramUploadVideoResult.class);
            
            if (!firstPhaseResult.getStatus().equalsIgnoreCase("ok")) {
                throw new RuntimeException("Error happened in video upload session start: " + firstPhaseResult.getMessage());
            }
    
            
            String uploadUrl = firstPhaseResult.getVideo_upload_urls().get(3).get("url").toString();
            String uploadJob = firstPhaseResult.getVideo_upload_urls().get(3).get("job").toString();
            
            StatusResult uploadJobResult = api.sendRequest(new InstagramUploadVideoJobRequest(uploadId, uploadUrl, uploadJob, videoFile));
            log.info("Upload result: " + uploadJobResult);
            
            if (!uploadJobResult.getStatus().equalsIgnoreCase("ok")) {
                throw new RuntimeException("Error happened in video upload submit job: " + uploadJobResult.getMessage());
            }
            
                
            StatusResult thumbnailResult = configureThumbnail(uploadId);
            
            if (!thumbnailResult.getStatus().equalsIgnoreCase("ok")) {
                throw new IllegalArgumentException("Failed to configure thumbnail: " + thumbnailResult.getMessage());
            }
            
            return api.sendRequest(new InstagramExposeRequest());

            
        }
    }

    /**
     * Configures the thumbnails for the given uploadId
     * @param uploadId The session id
     * @return Result
     * @throws Exception
     * @throws IOException
     * @throws ClientProtocolException
     */
    protected StatusResult configureThumbnail(String uploadId) throws Exception, IOException, ClientProtocolException {
        try (FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoFile)) {
            frameGrabber.start();
            Java2DFrameConverter converter = new Java2DFrameConverter();
            
            int width = frameGrabber.getImageWidth();
            int height = frameGrabber.getImageHeight();
            long length = frameGrabber.getLengthInTime();
            
            BufferedImage bufferedImage;
            if (thumbnailFile == null) {
                bufferedImage = MyImageUtils.deepCopy(converter.convert(frameGrabber.grabImage()));
                thumbnailFile = File.createTempFile("insta", ".jpg");
                
                log.info("Generated thumbnail: " + thumbnailFile.getAbsolutePath());
                ImageIO.write(bufferedImage, "JPG", thumbnailFile);
            } else {
                bufferedImage = ImageIO.read(thumbnailFile);
            }

            
            holdOn();
            
            StatusResult thumbnailResult = api.sendRequest(new InstagramUploadPhotoRequest(thumbnailFile, caption, uploadId));
            log.info("Thumbnail result: " + thumbnailResult);
            
            StatusResult configureResult = api.sendRequest(InstagramConfigureVideoRequest.builder().uploadId(uploadId)
                        .caption(caption)
                        .duration(length)
                        .width(width)
                        .height(height)
                        .build());
            
            log.info("Video configure result: " + configureResult);

            return configureResult;
        }
    }

    /**
     * Create the required multipart entity
     * @param uploadId Session ID
     * @return Entity to submit to the upload
     * @throws ClientProtocolException
     * @throws IOException
     */
    protected HttpEntity createMultipartEntity(String uploadId) throws ClientProtocolException, IOException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("upload_id", uploadId);
        builder.addTextBody("_uuid", api.getUuid());
        builder.addTextBody("_csrftoken", api.getOrFetchCsrf());
        builder.addTextBody("media_type", "2");
        builder.setBoundary(api.getUuid());

        HttpEntity entity = builder.build();
        return entity;
    }

    /**
     * @return http request 
     */
    protected HttpPost createHttpRequest() {
        String url = InstagramConstants.API_URL + getUrl();
        log.info("URL Upload: " + url);
        
        HttpPost post = new HttpPost(url);
        post.addHeader("X-IG-Capabilities", "3Q4=");
        post.addHeader("X-IG-Connection-Type", "WIFI");
        post.addHeader("Host", "i.instagram.com");
        post.addHeader("Cookie2", "$Version=1");
        post.addHeader("Accept-Language", "en-US");
        post.addHeader("Connection", "close");
        post.addHeader("Content-Type", "multipart/form-data; boundary=" + api.getUuid());
        post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
        return post;
    }

    /**
     * 
     */
    protected void holdOn() {
        //sad but helps to prevent Transcode Timeout
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
