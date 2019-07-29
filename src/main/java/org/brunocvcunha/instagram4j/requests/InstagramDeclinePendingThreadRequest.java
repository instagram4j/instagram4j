package org.brunocvcunha.instagram4j.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Decline a pending direct messaging request
 *
 * @author jemand771
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramDeclinePendingThreadRequest extends InstagramPostRequest<StatusResult> {

    private String threadId;

    @Override
    public String getUrl() {

        String baseUrl = "direct_v2/threads/" + threadId + "/decline/";
        return baseUrl;
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        Map<String, Object> approveMap = new LinkedHashMap<>();
        approveMap.put("_uuid", api.getUuid());
        approveMap.put("_csrftoken", api.getOrFetchCsrf());
        approveMap.put("_uid", api.getUserId());

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(approveMap);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }
}