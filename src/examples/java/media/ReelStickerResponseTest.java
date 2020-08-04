package media;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.media.MediaGetStoryPollVotersRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaGetStoryQuestionResponsesRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaListReelMediaViewerRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaGetStoryPollVotersResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaGetStoryQuestionResponsesResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// id 2364185211807618943
// question 17958669613337332
// poll 17958171793339158
public class ReelStickerResponseTest {
    @Test
    public void testMediaViewer() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = new MediaListReelMediaViewerRequest("2364185211807618943").execute(client);
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    public void testStoryQuestionResponses() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        MediaGetStoryQuestionResponsesResponse response = new MediaGetStoryQuestionResponsesRequest("2364185211807618943", "17958669613337332").execute(client);
        Assert.assertEquals("ok", response.getStatus());
        response.getResponder_info().getResponders().forEach(responder -> log.debug("{} : {}", responder.getResponse(), responder.getUser().getPk()));
        log.debug("Success");
    }
    
    @Test
    public void testStoryPollResponses() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        MediaGetStoryPollVotersResponse response = new MediaGetStoryPollVotersRequest("2364185211807618943", "17958171793339158").execute(client);
        Assert.assertEquals("ok", response.getStatus());
        response.getVoter_info().getVoters().forEach(responder -> log.debug("{} : {}", responder.getVote(), responder.getUser().getPk()));
        log.debug("Success");
    }
}
