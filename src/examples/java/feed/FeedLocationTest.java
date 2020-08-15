package feed;

import org.junit.Assert;
import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.feed.FeedLocationRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedLocationResponse;

import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class FeedLocationTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testName() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedLocationResponse response = new FeedLocationRequest(106048159426257l).execute(client).join();
        log.debug(response.getLocation().getName());
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
}
