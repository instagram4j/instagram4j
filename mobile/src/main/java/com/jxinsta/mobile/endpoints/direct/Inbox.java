package com.jxinsta.mobile.endpoints.direct;

import com.jxinsta.mobile.InstagramException;
import com.jxinsta.mobile.utils.Constants;
import com.jxinsta.mobile.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.org.json.JSONArray;
import android.org.json.JSONObject;

public class Inbox {
    private final String auth;
    public final int totalThreads;
    public final List<Thread> threads;
    public final List<String> users;


    public Inbox(String auth, JSONObject json) {
        this.auth = auth;

        // Extract threads
        JSONArray threadsArray = json.optJSONObject("inbox").optJSONArray("threads");
        this.totalThreads = threadsArray != null ? threadsArray.length() : 0;
        this.threads = new ArrayList<>();

        if (threadsArray != null) {
            for (int i = 0; i < threadsArray.length(); i++) {
                JSONObject threadJson = threadsArray.getJSONObject(i);
                this.threads.add(new Thread(auth,threadJson));
            }
        }

        // Extract users
        JSONArray usersArray = json.optJSONObject("inbox").optJSONArray("users");
        this.users = new ArrayList<>();
        if (usersArray != null) {
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                this.users.add(userJson.optString("username"));
            }
        }
    }

    private List<String> getOnlineUsers() throws InstagramException {
        return null;
        // Unimplemented
    }

    public List<Thread> getRequests(int maxThreads,int maxMessages) throws InstagramException {
        var params = new HashMap<String,Object>();
        params.put("visual_message_return_type", "all");
        params.put("limit", maxThreads);
        params.put("is_prefetching", false);
        params.put("thread_message_limit", maxMessages);
        var res = Utils.get(Constants.Endpoints.DM_REQUESTS, auth, params);
        var list = new ArrayList<Thread>();
        var threads = res.optJSONObject("inbox").optJSONArray("threads");
        for (int i = 0; i < threads.length(); i++) {
            list.add(new Thread(auth,threads.getJSONObject(i)));
        }
        return list;
    }

    public Thread getThread(String id,int maxMessage) throws InstagramException {
        var params = new HashMap<String,Object>();
        params.put("visual_message_return_type", "all");
        params.put("limit", maxMessage);
        params.put("direction", "older");
        var res = Utils.get(Constants.Endpoints.getThread(id), auth, params);
        return new Thread(auth,res.optJSONObject("thread"));
    }
}
