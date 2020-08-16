package highlights;

import org.junit.Assert;
import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.highlights.HighlightsCreateReelRequest;
import com.github.instagram4j.instagram4j.requests.highlights.HighlightsDeleteReelRequest;
import com.github.instagram4j.instagram4j.requests.highlights.HighlightsEditReelRequest;
import com.github.instagram4j.instagram4j.requests.highlights.HighlightsUserTrayRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class HighlightsTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testName() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = new HighlightsUserTrayRequest(18428658L).execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }

    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testCreate() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response =
                new HighlightsCreateReelRequest("EXAMPLE", "2342701632890283954_18428658",
                        "2343246908405232496_18428658").execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }

    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testDelete() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = new HighlightsDeleteReelRequest("highlight:17850277421192731")
                .execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }

    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testEdit() throws Exception {
        // highlight:17922135847428707
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = new HighlightsEditReelRequest("highlight:17922135847428707", "NEW",
                "2342701632890283954_18428658", new String[] {"2364174303060359549_18428658"}, null)
                        .execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
}
