package actions;

import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.users.UserAction;
import com.github.instagram4j.instagram4j.models.friendships.Friendship;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsActionRequest.FriendshipsAction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsersActionTest {
    @Test
    public void testFriendship() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        UserAction user = client.actions().users().findByUsername("kimkardashian");
        Friendship friendship = user.getFriendship();
        log.debug("Is following : {}", friendship.isFollowing());
        user.performAction(friendship.isFollowing() ? FriendshipsAction.DESTROY : FriendshipsAction.CREATE);
        log.debug("Success");
    }
}
