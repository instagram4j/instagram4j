package com.jxinsta.mobile.endpoints.profile;

import com.jxinsta.mobile.InstagramException;
import com.jxinsta.mobile.utils.Constants;
import com.jxinsta.mobile.utils.Utils;
import com.jxinsta.mobile.paginators.PostPaginator;
import com.jxinsta.mobile.paginators.ProfilePaginator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import android.org.json.JSONObject;

public class Profile extends ProfileData {
    private final String auth;

    public Profile(@NotNull JSONObject data, @NotNull String auth) {
        super(data);
        this.auth = auth;
    }

    public PostPaginator getPosts() {
        return new PostPaginator(auth, pk, null);
    }

    public ProfilePaginator getFollowers() {
        return new ProfilePaginator(auth, pk, "followers", null);
    }

    public ProfilePaginator getFollowings() {
        return new ProfilePaginator(auth, pk, "following", null);
    }

    public List<Story> getStory() throws InstagramException {
        var res = Utils.get(Constants.Endpoints.userReelMedia(pk), auth, null);
        var list = new ArrayList<Story>();
        if (res.has("items")) {
            var items = res.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                list.add(new Story(items.getJSONObject(i),auth));
            }
        }
        return list;
    }

    public List<Story> getHighlights() throws InstagramException {
        var res = Utils.get(Constants.Endpoints.userHighlightsTray(pk), auth, null);
        var list = new ArrayList<Story>();
        if (res.has("tray")) {
            var tray = res.getJSONArray("tray");
            for (int i = 0; i < tray.length(); i++) {
                var item = tray.getJSONObject(i);
                if (item.has("items")) {
                    var items = item.getJSONArray("items");
                    for (int j = 0; j < items.length(); j++) {
                        list.add(new Story(items.getJSONObject(j),auth));
                    }
                }
            }
        }
        return list;
    }

    public void follow() throws InstagramException {
        Utils.post(Constants.Endpoints.friendshipsCreate(pk), auth, null);
    }

    public void unfollow() throws InstagramException {
        Utils.post(Constants.Endpoints.friendshipsDestroy(pk), auth, null);
    }

    public void block() throws InstagramException {
        Utils.post(Constants.Endpoints.friendshipsBlock(pk), auth, null);
    }
}
