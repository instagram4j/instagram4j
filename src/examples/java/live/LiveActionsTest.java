package live;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.live.LiveBroadcastCommentRequest;
import com.github.instagram4j.instagram4j.requests.live.LiveBroadcastGetCommentRequest;
import com.github.instagram4j.instagram4j.requests.live.LiveBroadcastGetViewerListRequest;
import com.github.instagram4j.instagram4j.requests.live.LiveBroadcastHeartbeatRequest;
import com.github.instagram4j.instagram4j.requests.live.LiveBroadcastLikeRequest;
import com.github.instagram4j.instagram4j.requests.live.LiveBroadcastQuestionsRequest;
import com.github.instagram4j.instagram4j.requests.live.LiveWaveRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LiveActionsTest {
    private static final String BROADCAST_ID = "17845796883218625";
    
    @Test
    public void testComment() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        LiveBroadcastCommentRequest IGRequest = new LiveBroadcastCommentRequest(BROADCAST_ID, "Test");
        
        IGResponse response = IGRequest.execute(client);
        
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    public void testHeartbeat() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        LiveBroadcastHeartbeatRequest IGRequest = new LiveBroadcastHeartbeatRequest(BROADCAST_ID);
        IGResponse response = IGRequest.execute(client);
        
        Assert.assertEquals("ok", response.getStatus());
        log.debug("success");
    }
    
    @Test
    public void testGetComment() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        LiveBroadcastGetCommentRequest IGRequest = new LiveBroadcastGetCommentRequest(BROADCAST_ID);
        IGResponse response = IGRequest.execute(client);
        
        Assert.assertEquals("ok", response.getStatus());
        log.debug("success");
    }
    
    @Test
    public void testLike() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        LiveBroadcastLikeRequest IGRequest = new LiveBroadcastLikeRequest(BROADCAST_ID, 69);
        IGResponse response = IGRequest.execute(client);
        
        Assert.assertEquals("ok", response.getStatus());
        log.debug("success");
    }
    
    @Test
    public void testGetViewerList() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        LiveBroadcastGetViewerListRequest IGRequest = new LiveBroadcastGetViewerListRequest(BROADCAST_ID);
        IGResponse response = IGRequest.execute(client);
        
        Assert.assertEquals("ok", response.getStatus());
        log.debug("success");
    }
    
    @Test
    public void testBroadcastQuestions() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        LiveBroadcastQuestionsRequest IGRequest = new LiveBroadcastQuestionsRequest(BROADCAST_ID, "What is the meaning of life?");
        IGResponse response = IGRequest.execute(client);
        
        Assert.assertEquals("ok", response.getStatus());
        log.debug("success");
    }
    
    @Test
    public void testWave() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        LiveWaveRequest IGRequest = new LiveWaveRequest(BROADCAST_ID, "");
        IGResponse response = IGRequest.execute(client);
        
        Assert.assertEquals("ok", response.getStatus());
        log.debug("success");
    }
}
