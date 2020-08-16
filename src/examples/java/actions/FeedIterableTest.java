package actions;

import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.feed.FeedIterator;
import com.github.instagram4j.instagram4j.requests.feed.FeedTimelineRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedTimelineResponse;

import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class FeedIterableTest {

    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testIterator() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        // form a FeedIterator for FeedTimelineRequest
        FeedIterator<FeedTimelineResponse> iter =
                new FeedIterator<>(client, new FeedTimelineRequest());
        // setting a limit of 2 responses (initial and one paginated)
        int limit = 2;
        while (iter.hasNext() && limit-- > 0) {
            FeedTimelineResponse response = iter.next();
            // Actions here
            response.getFeed_items().forEach(m -> log.debug(m.getCaption().getText()));
            // Recommended to wait in between iterations
        }

        log.debug("Success");
    }

}
