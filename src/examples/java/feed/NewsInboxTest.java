package feed;

import org.junit.Assert;
import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.news.NewsInboxRequest;
import com.github.instagram4j.instagram4j.responses.news.NewsInboxResponse;

import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class NewsInboxTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testName() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        NewsInboxResponse response = new NewsInboxRequest().execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
        response.getOld_stories().forEach(story -> {
            log.debug(story.get("args").toString());
        });
        log.debug("Success");
    }
}
