package feed;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.feed.FeedItems;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineImageMedia;
import com.github.instagram4j.instagram4j.requests.feed.FeedTimelineRequest;
import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class FeedRequestTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testFeedRequest()
            throws IGResponseException, IGLoginException, ClassNotFoundException,
            FileNotFoundException, IOException {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        
        new FeedTimelineRequest().execute(client)
        .thenAccept(res -> {
            FeedItems.filter(res.getFeed_items(), TimelineImageMedia.class)// TimelineImageMedia correspends to media_type of 1
            .forEach(image -> {
                // now perform actions on image!
            });
        })
        .join();
        
        client.actions().timeline()
        .feed().stream()
        .limit(2)
        .forEach(res -> {
            FeedItems.filter(res.getFeed_items(), TimelineImageMedia.class)
            .forEach(image -> {
                log.info(image.getMedia_type());
                log.info("Download link : {}", image.getImage_versions2().getCandidates().get(0).getUrl());
            });
        });
    }
}
