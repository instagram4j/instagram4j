package com.jxinsta.web.endpoints.post;

import com.jxinsta.web.Constants;
import com.jxinsta.web.InstagramException;
import com.jxinsta.web.Utils;
import com.jxinsta.web.paginators.CommentPaginator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.org.json.JSONObject;

/**
 * Represents an Instagram post with interactive capabilities.
 * Extends {@link PostData} to include methods for liking, commenting, and fetching likers.
 */
public class Post extends PostData {
    protected final String session;
    protected final String crsf;

    /**
     * Internal constructor for Post.
     *
     * @param session   The session ID cookie.
     * @param crsf      The CSRF token.
     * @param postData  The JSON object containing post data.
     */
    public Post(@NotNull String session,@NotNull String crsf, @NotNull JSONObject postData) {
        super(postData);
        this.session = session;
        this.crsf = crsf;
    }

    /**
     * Likes this post.
     *
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public void like() throws InstagramException {
        Utils.postCall(session,crsf, Constants.Endpoints.postAction(this.id,"like"), null);
    }

    /**
     * Removes the like from this post.
     *
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public void dislike() throws InstagramException {
        Utils.postCall(session,crsf, Constants.Endpoints.postAction(this.id,"unlike"), null);
    }

    /**
     * Fetches the usernames of people who liked this post.
     *
     * @return A list of usernames.
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public List<String> likers() throws InstagramException {
        var json = Utils.getCall(Constants.Endpoints.likers(id), session);
        var likers = json.getJSONArray("users");
        var list = new ArrayList<String>();
        for (int i = 0; i < likers.length(); i++) {
            list.add(likers.getJSONObject(i).getString("username"));
        }
        return list;
    }

    /**
     * Returns a paginator for the comments on this post.
     *
     * @return A {@link CommentPaginator} for this post.
     */
    public CommentPaginator getComments() {
        return new CommentPaginator(session,crsf, id, null);
    }

    /**
     * Adds a comment to this post.
     *
     * @param comment The text of the comment.
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public void comment(@NotNull String comment) throws InstagramException {
        Utils.postCall(session,crsf, Constants.Endpoints.comments(id), Map.of("comment_text", comment));
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", shortcode='" + shortcode + '\'' +
                ", caption='" + caption + '\'' +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }

    /**
     * Supported media types for an Instagram post.
     */
    public enum MEDIA_TYPE {
        /** Video content. */
        VIDEO,
        /** Single image. */
        IMAGE,
        /** Multi-item post (slideshow). */
        CAROUSEL
    }
}
