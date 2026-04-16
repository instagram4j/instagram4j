package com.jxinsta.mobile.endpoints.post;

import com.jxinsta.mobile.InstagramException;
import com.jxinsta.mobile.utils.Constants;
import com.jxinsta.mobile.utils.Utils;
import com.jxinsta.mobile.utils.Likable;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import android.org.json.JSONObject;

public class Comment implements Likable {
    public final String id;
    public long mediaId;
    public String text;
    public String username;
    public int likes;
    public long timestamp;
    public final String auth;


    public Comment(String auth, JSONObject comment){
        this.auth = auth;
        this.text = comment.getString("text");
        this.username = comment.getJSONObject("user").getString("username");
        this.likes = comment.optInt("comment_like_count");
        this.timestamp = comment.getLong("created_at");
        this.id = comment.getString("pk");
        this.mediaId = comment.getLong("media_id");
    }

    @Override
    public void like() throws InstagramException {
        Utils.post(Constants.Endpoints.commentLike(id), auth, null);
    }


    public void dislike() throws InstagramException {
        Utils.post(Constants.Endpoints.commentUnlike(id), auth, null);
    }

    public void reply(@NotNull String reply) throws InstagramException {
        Map<String, String> body = new HashMap<>();
        body.put("comment_text", reply);
        body.put("replied_to_comment_id", id);
        Utils.post(Constants.Endpoints.mediaComment(String.valueOf(mediaId)), auth, body);
    }

    public void delete() throws InstagramException {
        Map<String, String> body = new HashMap<>();
        body.put("comment_ids_to_delete", id);
        Utils.post(Constants.Endpoints.commentBulkDelete(mediaId), auth, body);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", text='" + text + '\'' +
                ", username='" + username + '\'' +
                ", likes=" + likes +
                ", timestamp=" + timestamp +
                ", auth='" + auth + '\'' +
                '}';
    }
}
