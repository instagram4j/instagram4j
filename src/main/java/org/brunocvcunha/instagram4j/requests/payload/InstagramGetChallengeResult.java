package org.brunocvcunha.instagram4j.requests.payload;

public class InstagramGetChallengeResult extends StatusResult {
    private String step_name;
    private InstagramChallengeStepData step_data;
    private long user_id;
    private String nonce_code;

    public InstagramGetChallengeResult() {
    }

    public InstagramGetChallengeResult(String status, String step_name, InstagramChallengeStepData step_data, long user_id, String nonce_code) {
        super(status);
        this.step_name = step_name;
        this.step_data = step_data;
        this.user_id = user_id;
        this.nonce_code = nonce_code;
    }

    /* ... Getters and Setters ... */

    private class InstagramChallengeStepData {
        private int choice;
        private String fb_access_token;
        private String big_blue_token;
        private boolean google_oauth_token;
        private String phone_number;

        public InstagramChallengeStepData() {
        }

        public InstagramChallengeStepData(int choice, String fb_access_token, String big_blue_token, boolean google_oauth_token, String phone_number) {
            this.choice = choice;
            this.fb_access_token = fb_access_token;
            this.big_blue_token = big_blue_token;
            this.google_oauth_token = google_oauth_token;
            this.phone_number = phone_number;
        }

        /* ... Getters and Setters ... */
    }

    @Override
    public String toString() {
        return "InstagramGetChallengeResult{" +
                "step_name='" + step_name + '\'' +
                ", step_data=" + step_data +
                ", user_id=" + user_id +
                ", nonce_code='" + nonce_code + '\'' +
                '}';
    }
}
