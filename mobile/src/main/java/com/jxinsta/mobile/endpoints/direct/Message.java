package com.jxinsta.mobile.endpoints.direct;

import android.org.json.JSONArray;
import android.org.json.JSONObject;

public class Message {
    private final String id;
    public String text;
    public String mediaURL;
    public String postURL;
    public TYPE type;
    public boolean isReply;
    public boolean isSent;
    public Message replyOf;

    public Message(JSONObject item) {
        this.id = item.optString("item_id");
        this.isSent = item.optBoolean("is_sent_by_viewer", false);
        this.isReply = item.has("replied_to_message");

        if (isReply){
            replyOf = new Message(item.optJSONObject("replied_to_message"));
        }

        String itemType = item.optString("item_type");
        switch (itemType) {
            case "text":
                this.type = TYPE.TEXT;
                this.text = item.optString("text");
                break;

            case "media":
                JSONObject mediaObj = item.optJSONObject("media");
                if (mediaObj != null) {
                    int mediaType = mediaObj.optInt("media_type", 1);
                    if (mediaType == 2) {
                        this.type = TYPE.VIDEO;
                    } else {
                        this.type = TYPE.IMAGE;
                    }
                    JSONObject imageVersions = mediaObj.optJSONObject("image_versions2");
                    if (imageVersions != null) {
                        JSONArray candidates = imageVersions.optJSONArray("candidates");
                        if (candidates != null && !candidates.isEmpty()) {
                            this.mediaURL = candidates.getJSONObject(0).optString("url");
                        }
                    }
                }
                break;

            case "media_share":
                this.type = TYPE.POST;
                JSONObject shareObj = item.optJSONObject("direct_media_share");
                if (shareObj != null) {
                    JSONObject sharedMedia = shareObj.optJSONObject("media");
                    if (sharedMedia != null) {
                        JSONObject imageVersions = sharedMedia.optJSONObject("image_versions2");
                        if (imageVersions != null) {
                            JSONArray candidates = imageVersions.optJSONArray("candidates");
                            if (candidates != null && !candidates.isEmpty()) {
                                this.postURL = candidates.getJSONObject(0).optString("url");
                            }
                        }
                    }
                }
                break;

            default:
                this.type = TYPE.UNDEFINED;
                break;
        }
    }


    enum TYPE {
        TEXT,
        IMAGE,
        VIDEO,
        POST,
        UNDEFINED
    }
}


