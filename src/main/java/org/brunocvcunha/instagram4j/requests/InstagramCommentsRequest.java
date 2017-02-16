package weco.main;

import java.beans.ConstructorProperties;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

/**
 * 
 * @author Weco
 *
 */

@AllArgsConstructor
@Log4j
public class InstagramCommentsRequest extends InstagramPostRequest<InstagramCommentsResult> {

    private long mediaId;
    private String maxId;
    
    @Override
    public String getUrl() {
        String baseUrl = "media/" + mediaId + "/comments/";;
        if (maxId != null && !maxId.isEmpty()) {
            baseUrl += "?max_id=" + maxId;
        }
    	return baseUrl;
    }

    @Override
    @SneakyThrows
    public String getPayload() {
        
        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        try {
			likeMap.put("_csrftoken", api.getOrFetchCsrf());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = null;
		try {
			payloadJson = mapper.writeValueAsString(likeMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return payloadJson;
    }

	@ConstructorProperties({ "mediaId" })
	public InstagramCommentsRequest(long mediaId) {
		this.mediaId = mediaId;
	}
	@ConstructorProperties({ "userId", "maxId" })
	public InstagramCommentsRequest(long mediaId, String maxId) {
		this.mediaId = mediaId;
		this.maxId = maxId;
	}
	
    @Override
    @SneakyThrows
    public InstagramCommentsResult parseResult(int statusCode, String content) {
        return parseJson(content, InstagramCommentsResult.class);
    }
}
