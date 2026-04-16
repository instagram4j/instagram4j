package com.instagram4j.mobile.paginators;

import com.instagram4j.mobile.InstagramException;
import com.instagram4j.mobile.endpoints.profile.Profile;
import com.instagram4j.mobile.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ProfilePaginator implements Iterator<List<Profile>> {
    private final String auth;
    private final String pk;
    private final String endpoint;
    private String nextCursor;
    private boolean hasMore = true;

    public ProfilePaginator(String auth, String pk, String endpoint, String nextCursor) {
        this.auth = auth;
        this.pk = pk;
        this.endpoint = endpoint;
        this.nextCursor = nextCursor;
    }

    @Override
    public boolean hasNext() {
        return hasMore;
    }

    @Override
    public List<Profile> next() {
        if (!hasNext()) throw new NoSuchElementException();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("count", 200);
            if (nextCursor != null) {
                params.put("max_id", nextCursor);
            }

            var res = Utils.get("friendships/" + pk + "/" + endpoint + "/", auth, params);
            var users = res.getJSONArray("users");
            List<Profile> list = new ArrayList<>();
            for (int i = 0; i < users.length(); i++) {
                var userObj = users.getJSONObject(i);
                list.add(new Profile(userObj, auth));
            }

            if (res.has("next_max_id") && !users.isEmpty()) {
                nextCursor = res.getString("next_max_id");
                hasMore = true;
            } else {
                nextCursor = null;
                hasMore = false;
            }

            return list;
        } catch (InstagramException e) {
            throw new RuntimeException(e);
        }
    }
}
