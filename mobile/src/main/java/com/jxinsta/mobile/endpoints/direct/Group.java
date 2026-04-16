package com.jxinsta.mobile.endpoints.direct;

import java.util.ArrayList;
import java.util.List;

import android.org.json.JSONArray;
import android.org.json.JSONObject;

public class Group extends Thread {
    public List<String> recipients = new ArrayList<>();
    public List<String> leftUsers = new ArrayList<>();
    public List<Long> admins = new ArrayList<>();

    public Group(String auth, JSONObject thread) {
        super(auth, thread.optString("thread_id"));
        this.oldestCursor = thread.optString("oldest_cursor");

        JSONArray usersArray = thread.optJSONArray("users");
        if (usersArray != null) {
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                recipients.add(userJson.optString("username"));
            }
        }

        JSONArray itemsArray = thread.optJSONArray("items");
        if (itemsArray != null) {
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject itemJson = itemsArray.getJSONObject(i);
                messages.add(itemJson.optString("text"));
            }
        }

        JSONArray leftUsersArray = thread.optJSONArray("left_users");
        if (leftUsersArray != null) {
            for (int i = 0; i < leftUsersArray.length(); i++) {
                JSONObject leftUserJson = leftUsersArray.getJSONObject(i);
                leftUsers.add(leftUserJson.optString("username"));
            }
        }

        JSONArray adminsArray = thread.optJSONArray("admin_user_ids");
        if (adminsArray != null) {
            for (int i = 0; i < adminsArray.length(); i++) {
                var adminId = adminsArray.getLong(i);
                admins.add(adminId);
            }
        }

        var threadImage = thread.optJSONObject("thread_image")
                .optJSONObject("image_versions2")
                .optJSONArray("candidates")
                .getJSONObject(0);
        displayPicture = threadImage.optString("url");
    }

}
