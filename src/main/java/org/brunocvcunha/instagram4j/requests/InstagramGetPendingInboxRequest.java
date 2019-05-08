package org.brunocvcunha.instagram4j.requests;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.brunocvcunha.instagram4j.requests.payload.InstagramPendingInboxResult;

/**
 * Request to fetch "hidden" instagram inbox containing pending direct messages.
 * Messages from users you do not follow get sent here for the first time
 * Note that {@link org.brunocvcunha.instagram4j.requests.payload.InstagramInboxResult#pending_requests_total} already shows how many messages are in this pending inbox, 0 if none
 *
 * @author Willy Hille
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramGetPendingInboxRequest extends InstagramGetRequest<InstagramPendingInboxResult> {

    private String cursor;

    @Override
    public String getUrl() {

        String baseUrl = "direct_v2/pending_inbox/?";
        if (cursor != null && !cursor.isEmpty()) {
            baseUrl += "&cursor=" + cursor;
        }
        return baseUrl;
    }

    @Override
    @SneakyThrows
    public String getPayload() {
        return null;
    }

    @Override
    @SneakyThrows
    public InstagramPendingInboxResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramPendingInboxResult.class);
    }
}