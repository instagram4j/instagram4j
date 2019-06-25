package org.brunocvcunha.instagram4j.requests;


import org.brunocvcunha.instagram4j.requests.payload.InstagramGetChallengeResult;

public class InstagramGetChallengeRequest extends InstagramGetRequest<InstagramGetChallengeResult> {
    private String challengeUrl;

    public InstagramGetChallengeRequest(String challengeUrl) {
        this.challengeUrl = challengeUrl;
    }

    @Override
    public String getUrl() {
        return challengeUrl;
    }

    @Override
    public InstagramGetChallengeResult parseResult(int statusCode, String content) {
        try {
            return (InstagramGetChallengeResult)this.parseJson(statusCode, content, InstagramGetChallengeResult.class);
        } catch (Throwable var4) {
            throw var4;
        }
    }
}
