
package weco.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * 
 * @author Weco
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "friendship_status",
    "status"
})
public class InstagramDefaultResult {

    @JsonProperty("friendship_status")
    private FriendshipStatus friendshipStatus;
    @JsonProperty("status")
    private String status;

    @JsonProperty("friendship_status")
    public FriendshipStatus getFriendshipStatus() {
        return friendshipStatus;
    }

    @JsonProperty("friendship_status")
    public void setFriendshipStatus(FriendshipStatus friendshipStatus) {
        this.friendshipStatus = friendshipStatus;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

}
