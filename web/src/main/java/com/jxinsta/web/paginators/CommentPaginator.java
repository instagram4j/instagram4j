package com.jxinsta.web.paginators;

import com.jxinsta.web.Constants;
import com.jxinsta.web.InstagramException;
import com.jxinsta.web.Utils;
import com.jxinsta.web.endpoints.post.Comment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Paginator for fetching comments from an Instagram post.
 * Implements {@link Iterator} to provide pages of {@link Comment} objects.
 */
public class CommentPaginator implements Iterator<List<Comment>> {
    private final String session;
    private final String crsf;
    private final String mediaId;
    private String nextCursor;
    private boolean hasMore = true;

    /**
     * Internal constructor for CommentPaginator.
     *
     * @param session    The session ID cookie.
     * @param crsf       The CSRF token.
     * @param mediaId    The ID of the media/post whose comments are being fetched.
     * @param nextCursor The initial pagination cursor. Pass {@code null} for the first page.
     */
    public CommentPaginator(@NotNull String session,@NotNull String crsf, @NotNull String mediaId, @Nullable String nextCursor) {
        this.session = session;
        this.crsf = crsf;
        this.mediaId = mediaId;
        this.nextCursor = nextCursor;
    }

    /**
     * Checks if there are more pages of comments available.
     *
     * @return {@code true} if another page can be fetched, {@code false} otherwise.
     */
    @Override
    public boolean hasNext() {
        return hasMore;
    }

    /**
     * Fetches the next page of comments.
     *
     * @return A list of {@link Comment} objects for the current page.
     * @throws NoSuchElementException If no more pages are available.
     * @throws RuntimeException If an {@link InstagramException} occurs during the API call.
     */
    @Override
    public List<Comment> next() {
        if (!hasNext()) throw new NoSuchElementException();

        try {
            var url = Constants.Endpoints.comments(mediaId);
            if (nextCursor != null) {
                url += "&next_min_id=" + nextCursor;
            }

            var json = Utils.getCall(url, session);
            var comments = json.getJSONArray("comments");
            List<Comment> list = new ArrayList<>();
            for (int i = 0; i < comments.length(); i++) {
                list.add(new Comment(session,crsf, comments.getJSONObject(i), mediaId));
            }

            nextCursor = json.optString("next_min_id", null);
            hasMore = nextCursor != null && !comments.isEmpty();

            return list;
        } catch (InstagramException e) {
            throw new RuntimeException(e);
        }
    }
}
