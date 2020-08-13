package direct;

import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.requests.direct.DirectGetByParticipantsRequest;
import com.github.instagram4j.instagram4j.requests.direct.DirectGetPresenceRequest;
import com.github.instagram4j.instagram4j.requests.direct.DirectInboxRequest;
import com.github.instagram4j.instagram4j.requests.direct.DirectPendingInboxRequest;
import com.github.instagram4j.instagram4j.requests.direct.DirectThreadsRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.direct.DirectInboxResponse;
import com.github.instagram4j.instagram4j.responses.direct.DirectThreadsResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DirectInboxTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testInbox()
            throws IGLoginException, IGResponseException, IOException, ClassNotFoundException {
        IGClient lib = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        DirectInboxResponse response = new DirectInboxRequest().execute(lib).join();
        Assert.assertEquals("ok", response.getStatus());
        response.getInbox().getThreads().forEach(thread -> {
            log.debug("{} : {} : {}", thread.getThread_id(), thread.getThread_title(), thread.getUsers().stream().map(Profile::getUsername).collect(Collectors.joining(",")));
        });
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testPending() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = new DirectPendingInboxRequest().execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testThreads() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        String thread_id = "340282366841710300949128134036896195180";
        IGResponse response = client.sendRequest(new DirectThreadsRequest(thread_id)).join();
        Assert.assertEquals("ok", response.getStatus());
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testGetByParticipants() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        DirectThreadsResponse response = new DirectGetByParticipantsRequest(18428658L).execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testPresence() throws Exception {
        IGClient lib = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = lib.sendRequest(new DirectGetPresenceRequest()).join();
        Assert.assertEquals("ok", response.getStatus());
    }
}
