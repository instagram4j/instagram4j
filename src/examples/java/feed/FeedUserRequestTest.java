package feed;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.requests.feed.FeedUserRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedUserResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeedUserRequestTest {
    @Test // 2336729204844097508_18428658
    public void testFeedRequest()
            throws IGResponseException, IGLoginException, ClassNotFoundException, FileNotFoundException, IOException {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedUserRequest req = new FeedUserRequest(client.getSelfProfile().getPk());
        FeedUserResponse response = client.sendRequest(req);
        response.getItems().forEach(item -> {
            log.debug("{} : {}", item.getId(), item.getClass().getName());
        });
        Assert.assertEquals("ok", response.getStatus());
    }
}