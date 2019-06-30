package org.brunocvcunha.instagram4j.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.brunocvcunha.instagram4j.requests.payload.InstagramItem;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

import java.io.IOException;
import java.util.*;

public class InstagramWatchStoryRequest extends InstagramPostRequestV2<StatusResult> {

    private List<InstagramItem> reels;

    public InstagramWatchStoryRequest(final List<InstagramItem> items) {
        this.reels = items;
    }

    @Override
    public String getUrl() {
        return "media/seen/?reel=1&live_vod=0";
    }

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public String getPayload() {
        final Map<String, Object> params = new LinkedHashMap<>();

        params.put("_uuid", api.getUuid());
        params.put("_uid", api.getUserId());
        try {
            params.put("_csrftoken", api.getOrFetchCsrf());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        params.put("container_module", "feed_timeline");
        params.put("reels", parseReelsAndCalculateSeenAt(reels));
        params.put("reel_media_skipped", Collections.emptyList());
        params.put("live_vods", Collections.emptyList());
        params.put("live_vods_skipped", Collections.emptyList());
        params.put("nuxes", Collections.emptyList());
        params.put("nuxes_skipped", Collections.emptyList());

        final ObjectMapper mapper = new ObjectMapper();
        String payloadJson = null;
        try {
            payloadJson = mapper.writeValueAsString(params);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return payloadJson;
    }

    @Override
    public StatusResult parseResult(final int statusCode, final String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

    // taken from https://github.com/mgp25/Instagram-API/blob/master/src/Request/Internal.php
    private Map<String, List<String>> parseReelsAndCalculateSeenAt(final List<InstagramItem> reels) {
        final Map<String, List<String>> result = new LinkedHashMap<>();

        // Build the list of seen media, with human randomization of seen-time.

        final long maxSeenAt = System.currentTimeMillis() / 1000; // Get current global UTC timestamp.
        long seenAt = maxSeenAt - (5 * reels.size()); // Start seenAt in the past.

        for (InstagramItem reel : reels) {
            // Raise "seenAt" if it's somehow older than the item's "takenAt".
            // NOTE: Can only happen if you see a story instantly when posted.
            final long itemTakenAt = Long.valueOf(reel.getTaken_at());
            if (seenAt < itemTakenAt) {
                seenAt = itemTakenAt + 2;
            }

            // Do not let "seenAt" exceed the current global UTC time.
            if (seenAt > maxSeenAt) {
                seenAt = maxSeenAt;
            }

            // Determine the source ID for this item. This is where the item was
            // seen from, such as a UserID or a Location-StoryTray ID.
            final long itemSourceId = reel.getUser().getPk();

            // Key Format: "mediaPk_userPk_sourceId".
            // NOTE: In case of seeing stories on a user's profile, their
            // userPk is used as the sourceId, as "mediaPk_userPk_userPk".
            final String reelId = reel.getId() + "_" + itemSourceId;

            // Value Format: ["mediaTakenAt_seenAt"] (array with single string).
            result.put(reelId, Collections.singletonList(itemTakenAt + "_" + seenAt));

            // Randomly add 1-3 seconds to next seenAt timestamp, to act human.

            Random random = new Random();
            long seenSeconds = random.nextInt(5- 1) + 1;

            seenAt += seenSeconds;
        }

        return result;
    }

}
