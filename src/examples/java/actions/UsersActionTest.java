package actions;

import java.util.concurrent.ForkJoinPool;

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
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testFriendship() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        UserAction user = client.actions().users().findByUsername("kimkardashian").join();
        Friendship friendship = user.getFriendship().join();
        log.debug("Is following : {}", friendship.isFollowing());
        user.performAction(friendship.isFollowing() ? FriendshipsAction.DESTROY : FriendshipsAction.CREATE).join();
        log.debug("Success");
    }
}
