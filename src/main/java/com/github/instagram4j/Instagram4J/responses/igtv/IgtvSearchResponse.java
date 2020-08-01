package com.github.instagram4j.Instagram4J.responses.igtv;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.igtv.Channel;
import com.github.instagram4j.Instagram4J.models.user.User;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IgtvSearchResponse extends IGResponse {
    private List<IgtvSearchResult> results;
    private int num_results;
    private boolean has_more;
    private String rank_token;
    
    @Data
    public static class IgtvSearchResult {
        private String type;
        private User user;
        private Channel channel;
    }
}
