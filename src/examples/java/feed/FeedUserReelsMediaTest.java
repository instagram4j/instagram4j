package feed;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.requests.feed.FeedUserReelMediaRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

public class FeedUserReelsMediaTest {
    @Test
    public void testFeedRequest()
            throws IGResponseException, IGLoginException, ClassNotFoundException, FileNotFoundException, IOException {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedUserReelMediaRequest req = new FeedUserReelMediaRequest(18428658l);

        IGResponse response = client.sendRequest(req);
        Assert.assertEquals("ok", response.getStatus());
    }
}