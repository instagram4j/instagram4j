package org.brunocvcunha.instagram4j.requests;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;


/**
 * Mark a  direct messaging thread as Unread.
 *
 * @author Asnira
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramMarkThreadAsUnreadRequest extends InstagramPostRequest<StatusResult> {
    private String threadId;

    @Override
    public String getUrl() {

        String baseUrl = "direct_v2/threads/" + threadId + "/mark_unread/";
        return baseUrl;
    }

    @Override
    public String getPayload() {
        return "";
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }
}
