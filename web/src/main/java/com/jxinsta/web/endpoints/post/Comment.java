package com.jxinsta.web.endpoints.post;

import com.jxinsta.web.Constants;
import com.jxinsta.web.InstagramException;
import com.jxinsta.web.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import android.org.json.JSONObject;

/**
 * Represents a comment on an Instagram post. 
 * This class provides data about the comment and methods to interact with it (like, dislike, reply).
 */
public class Comment {
    /** The unique identifier (PK) of the comment. */
    public final String id;
    /** The media ID of the post this comment belongs to. */
    public String mediaId;
    /** The text content of the comment. */
    public String text;
    /** The username of the user who posted the comment. */
    public String username;
    /** The session ID for authentication. */
    public String session;
    /** The CSRF token for secure requests. */
    public String crsf;
    /** The number of likes this comment has received. */
    public int likes;
    /** The timestamp when the comment was created. */
    public long timestamp;

    /**
     * Internal constructor for Comment.
     *
     * @param session  The session ID cookie.
     * @param crsf     The CSRF token.
     * @param json     The JSON object containing comment data.
     * @param mediaId  The ID of the parent media/post.
     */
    public Comment(@NotNull String session,@NotNull String crsf, @NotNull JSONObject json, @NotNull String mediaId) {
        this.session = session;
        this.crsf = crsf;
        this.mediaId = mediaId;
        this.id = json.getString("pk");
        this.text = json.getString("text");
        this.username = json.getJSONObject("user").getString("username");
        this.likes = json.optInt("comment_like_count", 0);
        this.timestamp = json.getLong("created_at");
    }

    /**
     * Likes this comment.
     *
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public void like() throws InstagramException {
        Utils.postCall(session,crsf, Constants.Endpoints.commentAction(id, "like"), null);
    }

    /**
     * Removes the like from this comment.
     *
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public void dislike() throws InstagramException {
        Utils.postCall(session,crsf,Constants.Endpoints.commentAction(id,"unlike"), null);
    }

    /**
     * Replies to this comment.
     *
     * @param reply The text content of the reply.
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public void reply(@NotNull String reply) throws InstagramException {
        Utils.postCall(session,crsf, Constants.Endpoints.comments(mediaId), Map.of("comment_text", reply, "replied_to_comment_id", id));
    }
}
