package weco.requests;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.InstagramGetRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weco.results.InstagramInboxPendingResults;
/**
 * 
 * @author Weco
 *
 */

public class InstagramInboxPendingRequest extends InstagramGetRequest<InstagramInboxPendingResults> {
	
    private String maxId;
    
    @Override
    public String getUrl() {
    	
        String baseUrl = "direct_v2/pending_inbox/";
        if (maxId != null && !maxId.isEmpty()) {
            baseUrl += "?max_id=" + maxId;
        }
        return baseUrl;
    }

    @Override
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

    @Override
    public InstagramInboxPendingResults parseResult(int statusCode, String content) {
        return parseJson(content, InstagramInboxPendingResults.class);
    }
    
	public InstagramInboxPendingRequest() {
		
	}
    
	public InstagramInboxPendingRequest(String maxId) {
		this.maxId = maxId;
	}

    
}
