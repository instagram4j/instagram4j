package feed;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.requests.feed.FeedUserReelMediaRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import serialize.SerializeTestUtil;

public class FeedUserReelsMediaTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testFeedRequest()
            throws IGResponseException, IGLoginException, ClassNotFoundException,
            FileNotFoundException, IOException {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedUserReelMediaRequest req = new FeedUserReelMediaRequest(18428658l);

        IGResponse response = client.sendRequest(req).join();
        Assert.assertEquals("ok", response.getStatus());
    }
}
