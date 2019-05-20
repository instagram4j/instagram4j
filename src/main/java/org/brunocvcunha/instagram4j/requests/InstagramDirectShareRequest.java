/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests;

import lombok.Builder;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.instagram4j.util.InstagramGenericUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Direct-share request.
 *
 * @author Evgeny Bondarenko (evgbondarenko@gmail.com)
 */
@Log4j
@Builder(builderClassName = "DirectShareRequestBuilder", builderMethodName = "internalBuilder")
public class InstagramDirectShareRequest extends InstagramRequest<StatusResult> {
    @NonNull
    private ShareType shareType;
    /**
     * List of recipients IDs (i.e. "1234567890")
     */
    private List<String> recipients;
    /**
     * The thread ID to share to
     */
    private String threadId;
    /**
     * The media ID in instagram's internal format (ie "223322332233_22332").
     */
    private String mediaId;
    private String message;
    private List<String> link_urls;
    private String link_text;
    private File file;
    private String fileName;

    @Override
    public String getUrl() throws IllegalArgumentException {
        String result;
        switch (shareType) {
            case MESSAGE:
                result = "direct_v2/threads/broadcast/text/";
                break;
            case MEDIA:
                result = "direct_v2/threads/broadcast/media_share/?media_type=photo";
                break;
            // note: the api *does* support multiple links per message, though that is not compatible with the current data structures
            // please don't ask me how combined messages work (i.e. a link between other text)
            case LINK:
                result = "direct_v2/threads/broadcast/link/";
                break;
            case PHOTO:
                result = "direct_v2/threads/broadcast/upload_photo/";
                break;
            default:
                throw new IllegalArgumentException("Invalid shareType parameter value: " + shareType);
        }
        return result;
    }

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public StatusResult execute() throws ClientProtocolException, IOException {

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setBoundary(api.getUuid());

        if (shareType == ShareType.MEDIA) {
            StringBody mediaIdBody = new StringBody(mediaId, ContentType.MULTIPART_FORM_DATA);
            builder.addPart("media_id", mediaIdBody);
        }

        if (shareType == ShareType.LINK) {
            // this is the same array formatting magic as with the recipients
            String link_urls = "";
            link_urls = "\"" + String.join("\",\"", this.link_urls.toArray(new String[0])) + "\"";
            link_urls = "[" + link_urls + "]";

            StringBody linkUrlsBody = new StringBody(link_urls, ContentType.MULTIPART_FORM_DATA);
            builder.addPart("link_urls", linkUrlsBody);
            StringBody linkTextBody = new StringBody(link_text, ContentType.MULTIPART_FORM_DATA);
            builder.addPart("link_text", linkTextBody);
        }

        if (shareType == ShareType.PHOTO) {
            FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
            builder.addPart("photo", fileBody);
        }

        String recipients = "";
        if (this.recipients != null) {
            recipients = "\"" + String.join("\",\"", this.recipients.toArray(new String[0])) + "\"";
        }
        StringBody recipientsBody = new StringBody("[[" + recipients + "]]");
        builder.addPart("recipient_users", recipientsBody);

        StringBody clientContextBody = new StringBody(InstagramGenericUtil.generateUuid(true));
        builder.addPart("client_context", clientContextBody);

        // TODO actually support multiple threadIds
        StringBody threadIdsBody = new StringBody("[" + (threadId != null ? threadId : "") + "]");
        builder.addPart("thread_ids", threadIdsBody);

        StringBody messageBody = new StringBody(message == null ? "" : message);
        builder.addPart("text", messageBody);


        HttpPost post = createHttpRequest();
        HttpEntity entity = builder.build();
        post.setEntity(entity);

        try (CloseableHttpResponse response = api.getClient().execute(post)) {
            api.setLastResponse(response);

            int resultCode = response.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(response.getEntity());

            log.info("Direct-share request result: " + resultCode + ", " + content);

            post.releaseConnection();

            StatusResult result = parseResult(resultCode, content);

            return result;
        }
    }

    @Override
    public StatusResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, StatusResult.class);
    }

    protected HttpPost createHttpRequest() {
        String url = InstagramConstants.API_URL + getUrl();
        log.info("Direct-share URL: " + url);

        HttpPost post = new HttpPost(url);
        post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Proxy-Connection", "keep-alive");
        post.addHeader("Accept", "*/*");
        post.addHeader("Content-Type", "multipart/form-data; boundary=" + api.getUuid());
        post.addHeader("Accept-Language", "en-US");
        return post;
    }

    protected void init() {
        switch (shareType) {
            case MEDIA:
                if (mediaId == null || mediaId.isEmpty()) {
                    throw new IllegalArgumentException("mediaId cannot be null or empty.");
                }
                break;
            case MESSAGE:
                if (message == null || message.isEmpty()) {
                    throw new IllegalArgumentException("message cannot be null or empty.");
                }
                break;
            case LINK:
                // can link_text be null/empty?
                if (link_urls == null || link_urls.size() == 0) {
                    throw new IllegalArgumentException("link url cannot be empty");
                }
                break;
            case PHOTO:
                if(file == null)
                    throw new IllegalArgumentException("file can not be null");
                if(!file.exists())
                    // not using FileNotFoundException because technically, the argument is still invalid
                    throw new IllegalArgumentException("file " + file.getAbsolutePath() + " was not found");
                break;
            default:
                break;
        }
    }

    public static Builder builder(ShareType shareType) {
        Builder b = new Builder();
        b.shareType(shareType);
        return b;
    }

    public static Builder builder(ShareType shareType, List<String> recipients) {
        Builder b = new Builder();
        b.shareType(shareType).recipients(recipients);
        return b;
    }

    public static class Builder extends DirectShareRequestBuilder {
        Builder() {
            super();
        }

        @Override
        public InstagramDirectShareRequest build() {
            InstagramDirectShareRequest i = super.build();
            i.init();
            return i;
        }
    }

    public enum ShareType {
        MESSAGE, MEDIA, LINK, PHOTO
    }
}
