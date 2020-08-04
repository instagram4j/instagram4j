package igtv;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.igtv.IgtvBrowseFeedRequest;
import com.github.instagram4j.instagram4j.requests.igtv.IgtvChannelRequest;
import com.github.instagram4j.instagram4j.requests.igtv.IgtvSearchRequest;
import com.github.instagram4j.instagram4j.requests.igtv.IgtvSeriesAddEpisodeRequest;
import com.github.instagram4j.instagram4j.requests.igtv.IgtvSeriesCreateRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.igtv.IgtvChannelResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IgtvTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSearch() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IgtvSearchRequest req = new IgtvSearchRequest("prank");
        IGResponse res = client.sendRequest(req);
        Assert.assertEquals("ok", res.getStatus());
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testFeed() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IgtvBrowseFeedRequest req = new IgtvBrowseFeedRequest("");
        IGResponse res = client.sendRequest(req);
        Assert.assertEquals("ok", res.getStatus());
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testChannel() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IgtvChannelRequest req = new IgtvChannelRequest("user_18428658");
        IgtvChannelResponse res = client.sendRequest(req);
        Assert.assertEquals("ok", res.getStatus());
        log.debug(res.toString());
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSeriesCreate() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IgtvSeriesCreateRequest req = new IgtvSeriesCreateRequest("Earth's Videos", "A collection of cool videos");
        IGResponse res = client.sendRequest(req);
        Assert.assertEquals("ok", res.getStatus());
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSeriesAdd() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        String series_id = "17893711502749460";
        long media_id = 2345536212352661506L;
        IgtvSeriesAddEpisodeRequest req = new IgtvSeriesAddEpisodeRequest(series_id, media_id);
        IGResponse res = client.sendRequest(req);
        Assert.assertEquals("ok", res.getStatus());
    }
}
