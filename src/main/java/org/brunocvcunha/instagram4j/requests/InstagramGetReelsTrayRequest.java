package org.brunocvcunha.instagram4j.requests;

import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaInfoResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetReelsTrayResult;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

import lombok.SneakyThrows;

/**
 * Get Story Request
 *
 * @author Ozan Karaali
 *
 */
public class InstagramGetReelsTrayRequest extends InstagramGetRequest<InstagramGetReelsTrayResult>{
    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public String getUrl() {
        return "feed/reels_tray/?";
    }

    @Override
    @SneakyThrows
    public InstagramGetReelsTrayResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramGetReelsTrayResult.class);
    }
}
