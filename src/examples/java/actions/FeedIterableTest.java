package actions;

import org.junit.Assert;
import org.junit.Test;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.feed.CursorIterator;
import com.github.instagram4j.instagram4j.actions.feed.FeedIterable;
import com.github.instagram4j.instagram4j.requests.direct.DirectInboxRequest;
import com.github.instagram4j.instagram4j.requests.feed.FeedTimelineRequest;
import com.github.instagram4j.instagram4j.responses.direct.DirectInboxResponse;
import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class FeedIterableTest {

    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testIterator() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        // form a FeedIterator for FeedTimelineRequest
        new FeedIterable<>(client, () -> new FeedTimelineRequest())
        .stream()
        .limit(2)
        .forEach(res -> {
            log.info("{} {}", res.getStatus(), res.getFeed_items().size());
        });
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testCursorIterator() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        CursorIterator<DirectInboxRequest, DirectInboxResponse> iterator 
        = new CursorIterator<>(client, new DirectInboxRequest(), (x, s) -> x.cursor(s), (res) -> res.getInbox().getOldest_cursor(), (res) -> res.getInbox().getOldest_cursor() != null);
        
        int limit = 2;
        while(iterator.hasNext() && limit-- > 0) {
            DirectInboxResponse res = iterator.next();
            
            Assert.assertEquals("ok", res.getStatus());
            log.debug(res.getInbox().getOldest_cursor());
        }
        
        log.debug("Success");
    }

}
