package friendships;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsActionRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsActionRequest.FriendshipsAction;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsFeedsRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsFeedsRequest.FriendshipsFeeds;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsPendingRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsSetBestiesRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsShowManyRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsShowRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.feed.FeedUsersResponse;
import com.github.instagram4j.instagram4j.responses.friendships.FriendshipsShowManyResponse;
import com.github.instagram4j.instagram4j.responses.friendships.FriendshipsShowResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FriendshipsTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testShow() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FriendshipsShowRequest show = new FriendshipsShowRequest(18428658l);
        FriendshipsShowResponse response = show.execute(client);
        Assert.assertEquals("ok", response.getStatus());
        log.debug(response.getFriendship().isFollowed_by() + "");
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testShowMany() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FriendshipsShowManyRequest show = new FriendshipsShowManyRequest(18428658l, 18428658l);
        FriendshipsShowManyResponse response = show.execute(client);
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testActions() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response =  new FriendshipsActionRequest(18428658l, FriendshipsAction.APPROVE).execute(client);
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testBesties() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response =  new FriendshipsSetBestiesRequest(true, 18428658L, 18428658L).execute(client);
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testFollowers() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedUsersResponse response =  new FriendshipsFeedsRequest(18428658L, FriendshipsFeeds.FOLLOWING).execute(client);
        Assert.assertEquals("ok", response.getStatus());
        response.getUsers().forEach(u -> log.debug("{} : {}", u.getPk(), u.getUsername()));
        response = new FriendshipsFeedsRequest(18428658L, FriendshipsFeeds.FOLLOWING, response.getNext_max_id()).execute(client);
        response.getUsers().forEach(u -> log.debug("{} : {}", u.getPk(), u.getUsername()));
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testFeeds() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedUsersResponse response =  new FriendshipsPendingRequest().execute(client);
        Assert.assertEquals("ok", response.getStatus());
        response.getUsers().forEach(u -> log.debug("{} : {}", u.getPk(), u.getUsername()));
        log.debug("Success");
    }
}
