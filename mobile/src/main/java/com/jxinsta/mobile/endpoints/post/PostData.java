package com.jxinsta.mobile.endpoints.post;

import android.org.json.JSONObject;

public class PostData {
    public Post.MEDIA_TYPE mediaType;
    public String shortcode;
    public String[] download_url;
    public String caption;
    public int likes;
    public int comments;
    public String id;

    public PostData(){

    }

    public PostData (JSONObject postData){
        this.id = postData.optString("pk", postData.optString("id"));
        this.shortcode = postData.optString("code");
        int type = postData.optInt("media_type");
        if (type == 1) mediaType = Post.MEDIA_TYPE.IMAGE;
        else if (type == 2) mediaType = Post.MEDIA_TYPE.VIDEO;
        else if (type == 8) mediaType = Post.MEDIA_TYPE.CAROUSEL;

        if (postData.has("caption") && !postData.isNull("caption")) {
            this.caption = postData.getJSONObject("caption").optString("text");
        }
        this.likes = postData.optInt("like_count");
        this.comments = postData.optInt("comment_count");

        if (postData.has("image_versions2")) {
            var candidates = postData.getJSONObject("image_versions2").getJSONArray("candidates");
            this.download_url = new String[]{candidates.getJSONObject(0).getString("url")};
        } else if (postData.has("carousel_media")) {
            var carousel = postData.getJSONArray("carousel_media");
            this.download_url = new String[carousel.length()];
            for (int i = 0; i < carousel.length(); i++) {
                this.download_url[i] = carousel.getJSONObject(i).getJSONObject("image_versions2").getJSONArray("candidates").getJSONObject(0).getString("url");
            }
        }
    }

    public static PostData buildForUserPost(JSONObject node) {
        return new PostData(node);
    }
}
