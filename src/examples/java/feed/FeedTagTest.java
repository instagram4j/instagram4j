package feed;

import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.feed.FeedTagRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedTagResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeedTagTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testName() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedTagResponse response = client.sendRequest(new FeedTagRequest("love"));
        log.debug("Items : {} Story : {}", response.getItems().size(), response.getStory().getItems().size());
        response = new FeedTagRequest("love", response.getNext_max_id()).execute(client);
        log.debug("Items : {} Story : {}", response.getItems().size(), response.getStory().getItems().size());
        log.debug("Success");
    }
}
