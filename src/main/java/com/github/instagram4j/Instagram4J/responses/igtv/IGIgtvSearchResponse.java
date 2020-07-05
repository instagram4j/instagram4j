package com.github.instagram4j.Instagram4J.responses.igtv;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.igtv.IGChannel;
import com.github.instagram4j.Instagram4J.models.user.IGUser;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGIgtvSearchResponse extends IGResponse {
    private List<IGIgtvSearchResult> results;
    private int num_results;
    private boolean has_more;
    private String rank_token;
    
    @Data
    public static class IGIgtvSearchResult {
        private String type;
        private IGUser user;
        private IGChannel channel;
    }
}
