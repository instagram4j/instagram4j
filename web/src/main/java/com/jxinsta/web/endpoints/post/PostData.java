package com.jxinsta.web.endpoints.post;

import com.jxinsta.web.Constants;
import android.org.json.JSONObject;

/**
 * A data class representing basic information about an Instagram post.
 * This class is typically used for read-only operations or as a base for interactive {@link Post} objects.
 */
public class PostData {
    /** The type of media (IMAGE, VIDEO, or CAROUSEL). */
    public Post.MEDIA_TYPE mediaType;
    /** The shortcode of the post (e.g., the "CODE" in /p/CODE/). */
    public String shortcode;
    /** An array of URLs to download the media. For carousels, this contains all items. */
    public String[] download_url;
    /** The caption of the post. */
    public String caption;
    /** The number of likes on the post. */
    public int likes;
    /** The number of comments on the post. */
    public int comments;
    /** The unique identifier (PK) of the post. */
    public String id;

    /**
     * Constructs a PostData object from a JSON node.
     *
     * @param node The JSON object containing post data from Instagram's API.
     */
    public PostData(JSONObject node) {
        this.id = node.optString("pk", node.optString("id"));
        this.shortcode = node.optString("code", node.optString("shortcode"));
        
        if (node.has("edge_media_to_caption")) {
            var edges = node.getJSONObject("edge_media_to_caption").getJSONArray("edges");
            if (!edges.isEmpty()) {
                this.caption = edges.getJSONObject(0).getJSONObject("node").getString("text");
            }
        } else if (node.has("caption") && !node.isNull("caption")) {
            this.caption = node.getJSONObject("caption").optString("text");
        }

        this.likes = node.optInt("like_count", node.optJSONObject("edge_media_preview_like") != null ? node.optJSONObject("edge_media_preview_like").optInt("count") : 0);
        this.comments = node.optInt("comment_count", node.optJSONObject("edge_media_to_comment") != null ? node.optJSONObject("edge_media_to_comment").optInt("count") : 0);

        int type = node.optInt("media_type", -1);
        if (type != -1) {
            this.mediaType = Constants.mediaTypes.get(type);
        } else {
            this.mediaType = node.optJSONObject("edge_sidecar_to_children") == null ? node.optBoolean("is_video") ? Post.MEDIA_TYPE.VIDEO : Post.MEDIA_TYPE.IMAGE : Post.MEDIA_TYPE.CAROUSEL;
        }

        if (this.mediaType == Post.MEDIA_TYPE.CAROUSEL) {
            if (node.has("edge_sidecar_to_children")) {
                var edges = node.getJSONObject("edge_sidecar_to_children").getJSONArray("edges");
                this.download_url = new String[edges.length()];
                for (int i = 0; i < edges.length(); i++) {
                    var n = edges.getJSONObject(i).getJSONObject("node");
                    this.download_url[i] = n.optBoolean("is_video") ? n.optString("video_url") : n.optString("display_url");
                }
            } else if (node.has("carousel_media")) {
                var items = node.getJSONArray("carousel_media");
                this.download_url = new String[items.length()];
                for (int i = 0; i < items.length(); i++) {
                    var item = items.getJSONObject(i);
                    this.download_url[i] = item.optInt("media_type") == 2 ? 
                            item.optJSONObject("video_versions") != null ? item.getJSONArray("video_versions").getJSONObject(0).getString("url") : "" :
                            item.getJSONObject("image_versions2").getJSONArray("candidates").getJSONObject(0).getString("url");
                }
            }
        } else {
            this.download_url = new String[1];
            if (node.has("video_versions")) {
                var vv = node.optJSONArray("video_versions");
                if (vv != null)
                    this.download_url[0] = vv.getJSONObject(0).getString("url");
            } else if (node.has("image_versions2")) {
                var iv2 = node.optJSONObject("image_versions2");
                if (iv2 != null)
                    this.download_url[0] = iv2.getJSONArray("candidates").getJSONObject(0).getString("url");
            } else {
                this.download_url[0] = node.optString("video_url", node.optString("display_url"));
            }
        }
    }

    /**
     * Factory method to build a PostData object from a user post node.
     *
     * @param node The JSON object containing post data.
     * @return A new PostData instance.
     */
    public static PostData buildForUserPost(JSONObject node) {
        return new PostData(node);
    }
}
