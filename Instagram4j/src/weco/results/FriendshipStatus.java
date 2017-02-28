
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
    "is_private",
    "incoming_request",
    "blocking",
    "following",
    "outgoing_request",
    "followed_by"
})
public class FriendshipStatus {

    @JsonProperty("is_private")
    private Boolean isPrivate;
    @JsonProperty("incoming_request")
    private Boolean incomingRequest;
    @JsonProperty("blocking")
    private Boolean blocking;
    @JsonProperty("following")
    private Boolean following;
    @JsonProperty("outgoing_request")
    private Boolean outgoingRequest;
    @JsonProperty("followed_by")
    private Boolean followedBy;

    @JsonProperty("is_private")
    public Boolean getIsPrivate() {
        return isPrivate;
    }

    @JsonProperty("is_private")
    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    @JsonProperty("incoming_request")
    public Boolean getIncomingRequest() {
        return incomingRequest;
    }

    @JsonProperty("incoming_request")
    public void setIncomingRequest(Boolean incomingRequest) {
        this.incomingRequest = incomingRequest;
    }

    @JsonProperty("blocking")
    public Boolean getBlocking() {
        return blocking;
    }

    @JsonProperty("blocking")
    public void setBlocking(Boolean blocking) {
        this.blocking = blocking;
    }

    @JsonProperty("following")
    public Boolean getFollowing() {
        return following;
    }

    @JsonProperty("following")
    public void setFollowing(Boolean following) {
        this.following = following;
    }

    @JsonProperty("outgoing_request")
    public Boolean getOutgoingRequest() {
        return outgoingRequest;
    }

    @JsonProperty("outgoing_request")
    public void setOutgoingRequest(Boolean outgoingRequest) {
        this.outgoingRequest = outgoingRequest;
    }

    @JsonProperty("followed_by")
    public Boolean getFollowedBy() {
        return followedBy;
    }

    @JsonProperty("followed_by")
    public void setFollowedBy(Boolean followedBy) {
        this.followedBy = followedBy;
    }

}
