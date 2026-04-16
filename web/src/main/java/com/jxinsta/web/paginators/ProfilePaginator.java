package com.jxinsta.web.paginators;

import com.jxinsta.web.InstagramException;
import com.jxinsta.web.JxInsta;
import com.jxinsta.web.Utils;
import com.jxinsta.web.endpoints.profile.Profile;
import com.jxinsta.web.endpoints.profile.ProfileData;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Paginator for fetching lists of profiles, such as followers or following.
 * Implements {@link Iterator} to provide pages of {@link Profile} objects.
 */
public class ProfilePaginator implements Iterator<List<Profile>> {
    private final String pk;
    private final String session;
    private final String crsf;
    private final String endpoint;
    private String nextCursor;
    private boolean hasMore = true;

    /**
     * Internal constructor for ProfilePaginator.
     *
     * @param session    The session ID cookie.
     * @param crsf       The CSRF token.
     * @param pk         The numeric ID (pk) of the user.
     * @param endpoint   The specific endpoint for the profile list (e.g., "followers", "following").
     * @param nextCursor The initial pagination cursor. Pass {@code null} for the first page.
     */
    public ProfilePaginator(@NotNull String session,@NotNull String crsf, @NotNull String pk, @NotNull String endpoint, @Nullable String nextCursor) {
        this.session = session;
        this.crsf = crsf;
        this.pk = pk;
        this.endpoint = endpoint;
        this.nextCursor = nextCursor;
    }

    /**
     * Checks if there are more pages of profiles available.
     *
     * @return {@code true} if another page can be fetched, {@code false} otherwise.
     */
    @Override
    public boolean hasNext() {
        return hasMore;
    }

    /**
     * Fetches the next page of profiles.
     *
     * @return A list of {@link Profile} objects for the current page.
     * @throws NoSuchElementException If no more pages are available.
     * @throws RuntimeException If an {@link InstagramException} occurs during the API call.
     */
    @Override
    public List<Profile> next() {
        if (!hasNext()) throw new NoSuchElementException();

        try {
            var url = "friendships/" + pk + "/" + endpoint + "/?count=50";
            if (nextCursor != null) {
                url += "&maxId=" + nextCursor;
            }

            var res = Utils.getCall(url,session);
            var users = res.getJSONArray("users");
            List<Profile> list = new ArrayList<>();
            for (int i = 0; i < users.length(); i++) {
                var userObj = users.getJSONObject(i);
                list.add(new Profile(userObj, session,crsf));
            }

            nextCursor = res.optString("next_max_id", null);
            hasMore = nextCursor != null && !users.isEmpty();

            return list;
        } catch (InstagramException e) {
            throw new RuntimeException(e);
        }
    }
}
