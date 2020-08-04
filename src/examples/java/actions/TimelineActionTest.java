package actions;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.feed.FeedIterator;
import com.github.instagram4j.instagram4j.actions.timeline.TimelineAction.SidecarInfo;
import com.github.instagram4j.instagram4j.actions.timeline.TimelineAction.SidecarPhoto;
import com.github.instagram4j.instagram4j.actions.timeline.TimelineAction.SidecarVideo;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureSidecarRequest.MediaConfigureSidecarPayload;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.feed.FeedTimelineResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimelineActionTest {
    @Test
    public void testFeed() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedIterator<FeedTimelineResponse> iter = client.actions().timeline().feed();
        int runs = 2;
        while (iter.hasNext() && runs-- > 0) {
            FeedTimelineResponse response = iter.next();
            response.getFeed_items().forEach(m -> log.debug(m.getCaption().getText()));
        }
        log.debug("Success");
    }
    
    @Test
    public void testPhoto() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = client.actions().timeline().uploadPhoto(new File("src/examples/resources/test.jpg"), "Nice photo");
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    public void testVideo() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = client.actions().timeline().uploadVideo(new File("src/examples/resources/test.mp4"), new File("src/examples/resources/cover.jpg"), "Nice photo");
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    public void testAlbum() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        List<SidecarInfo> info = Arrays.asList(SidecarPhoto.from(new File("src/examples/resources/cover.jpg")), SidecarVideo.from(new File("src/examples/resources/test.mp4"), new File("src/examples/resources/cover.jpg")));
        IGResponse response = client.actions().timeline().uploadAlbum(info, new MediaConfigureSidecarPayload().caption("Nice album!"));
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
}
