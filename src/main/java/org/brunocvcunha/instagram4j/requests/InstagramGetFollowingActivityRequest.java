package org.brunocvcunha.instagram4j.requests;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetFollowingActivityResponse;

/**
 * Get Following Activity Request(News)
 * Get news feed of accounts the logged in account is following.
 * This returns the items in the 'Following' tab
 *
 * @author dnshost
 */
@AllArgsConstructor
public class InstagramGetFollowingActivityRequest extends InstagramGetRequest<InstagramGetFollowingActivityResponse> {
    /**
     * Id of last media
     */
    private String maxId;

    @Override
    @SneakyThrows
    public String getUrl() {
        String url = "news/";
        if (maxId != null && !maxId.isEmpty()) {
            url += "?max_id=" + maxId;
        }
        return url;
    }

    @Override
    @SneakyThrows
    public InstagramGetFollowingActivityResponse parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramGetFollowingActivityResponse.class);
    }


}
