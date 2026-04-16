package com.jxinsta.mobile.paginators;

import com.jxinsta.mobile.InstagramException;
import com.jxinsta.mobile.endpoints.post.Comment;
import com.jxinsta.mobile.endpoints.post.Post;
import com.jxinsta.mobile.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


public class CommentPaginator implements Iterator<List<Comment>> {
    public String cursor;
    private final String id;
    private final String auth;
    private boolean canFetchMore = true;

    public CommentPaginator(String id, String auth) {
        this.id = id;
        this.auth = auth;
    }

    @Override
    public boolean hasNext() {
        return canFetchMore;
    }

    @Override
    public List<Comment> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("can_support_threading", "false");
            if (cursor != null) {
                params.put("min_id", cursor);
            }
            var json = Utils.get("media/" + id + "/comments/", auth, params);
            if (json.has("next_min_id")) {
                cursor = json.getString("next_min_id");
                canFetchMore = true;
            } else {
                cursor = null;
                canFetchMore = false;
            }

            var commentsJson = json.getJSONArray("comments");
            List<Comment> list = new ArrayList<>();
            for (int i = 0; i < commentsJson.length(); i++) {
                var cj = commentsJson.getJSONObject(i);
                list.add(new Comment(auth,cj));
            }
            return list;
        } catch (InstagramException e) {
            throw new RuntimeException(e);
        }
    }
}