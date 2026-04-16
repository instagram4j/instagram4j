package com.jxinsta.mobile.endpoints.direct;

import com.jxinsta.mobile.InstagramException;
import com.jxinsta.mobile.paginators.MessagePaginator;
import com.jxinsta.mobile.utils.Constants;
import com.jxinsta.mobile.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.org.json.JSONArray;
import android.org.json.JSONObject;

public class Thread {
    private final String auth;
    public final String id;
    public String recipient;
    public long recipientId;
    public String displayPicture;
    public final List<String> messages = new ArrayList<>();
    public String oldestCursor;
    public String lastItemId;

    public Thread(String auth, JSONObject thread) {
        this.auth = auth;
        this.id = thread.optString("thread_id");
        this.oldestCursor = thread.optString("oldest_cursor");

        var users = thread.optJSONArray("users");
        if (users != null && !users.isEmpty()) {
            var user = users.getJSONObject(0);
            recipient = user.getString("username");
            recipientId = user.optLong("pk");
            displayPicture = user.optString("profile_pic_url_hd");
        }

        JSONArray itemsArray = thread.optJSONArray("items");
        if (itemsArray != null && !itemsArray.isEmpty()) {
            lastItemId = itemsArray.getJSONObject(0).optString("item_id");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject itemJson = itemsArray.getJSONObject(i);
                messages.add(itemJson.optString("text"));
            }
        }
    }

    public Thread(String auth, String id) {
        this.auth = auth;
        this.id = id;
    }


    public void sendMessage(@NotNull String message) throws InstagramException {
        Map<String, Object> body = new HashMap<>();
        body.put("action", "send_item");
        body.put("is_x_transport_forward", "false");
        body.put("is_shh_mode", "0");
        body.put("send_silently", "false");
        body.put("recipient_users", recipientId == 0 ? "[[]]" : "[[" + recipientId + "]]");
        body.put("send_attribution", "inbox");
        body.put("client_context", UUID.randomUUID().toString());
        body.put("text", message);
        body.put("thread_ids", "[\"" + id + "\"]");

        Utils.post(Constants.Endpoints.SEND_MESSAGE, auth, Utils.genSignedBody(body));
    }

    public void sendImage(@NotNull InputStream inputStream, @NotNull String caption) throws InstagramException {
        try {
            String uploadId = Utils.uploadPicture(inputStream, auth);
            Map<String, Object> body = new HashMap<>();
            body.put("action", "send_item");
            body.put("is_x_transport_forward", "false");
            body.put("recipient_users", recipientId == 0 ? "[[]]" : "[[" + recipientId + "]]");
            body.put("send_attribution", "inbox");
            body.put("attachment_fbid", uploadId);
            body.put("allow_full_aspect_ratio", "true");
            body.put("thread_ids", "[\"" + id + "\"]");
            body.put("text", caption);

            Utils.post(Constants.Endpoints.SEND_PHOTO, auth, Utils.genSignedBody(body));
        } catch (IOException e) {
            throw new InstagramException(e.getMessage(), InstagramException.Reasons.IO);
        }
    }

    public MessagePaginator getMessages() {
        return new MessagePaginator(auth, id, oldestCursor);
    }

    public void delete() throws InstagramException {
        Map<String, Object> body = new HashMap<>();
        body.put("should_move_future_requests_to_spam", "false");
        Utils.post(Constants.Endpoints.deleteThread(id), auth, Utils.genSignedBody(body));
    }

    public void markSeen() throws InstagramException {
        if (lastItemId != null) {
            Utils.post(Constants.Endpoints.markSeen(this.id, lastItemId), auth, null);
        }
    }

    @Override
    public String toString() {
        return "Thread{" +
                "auth='" + auth + '\'' +
                ", id='" + id + '\'' +
                ", recipient='" + recipient + '\'' +
                ", recipientId=" + recipientId +
                ", displayPicture='" + displayPicture + '\'' +
                ", messages=" + messages +
                ", oldestCursor='" + oldestCursor + '\'' +
                ", lastItemId='" + lastItemId + '\'' +
                '}';
    }
}
