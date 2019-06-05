package org.brunocvcunha.instagram4j.requests;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetReelsTrayFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserReelMediaFeedResult;

@AllArgsConstructor
public class InstagramGetUserReelMediaFeedRequest extends InstagramGetRequest<InstagramUserReelMediaFeedResult> {

    private long userPk;

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public String getUrl() {
        return "feed/user/" + userPk + "/reel_media/";
    }

    @Override
    @SneakyThrows
    public InstagramUserReelMediaFeedResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramUserReelMediaFeedResult.class);
    }

}
