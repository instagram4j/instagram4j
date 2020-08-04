package feed;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.feed.FeedSavedRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedSavedResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeedSavedTest {
    @Test
    public void testName() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedSavedResponse response = new FeedSavedRequest().execute(client);
        Assert.assertEquals("ok", response.getStatus());
        response.getItems().forEach(item -> log.debug("{} : {}", item.getId(), item.getClass().getName()));
        log.debug("Success");
    }
}
