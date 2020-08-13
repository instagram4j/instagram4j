package feed;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.requests.feed.FeedUserStoryRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedUserStoryResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeedUserStoryTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testFeedRequest()
            throws IGResponseException, IGLoginException, ClassNotFoundException, FileNotFoundException, IOException {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedUserStoryRequest req = new FeedUserStoryRequest(18428658l);

        FeedUserStoryResponse response = client.sendRequest(req).join();
        log.debug("Broadcast id : {}", response.getBroadcast() != null ? response.getBroadcast().getId() : "No broadcast found.");
        Assert.assertEquals("ok", response.getStatus());
    }
}