package actions;

import org.junit.Assert;
import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class SearchActionTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSearchTag() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = client.actions().search().searchTag("love").join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSearchLocation() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = client.actions().search().searchPlace("San Francisco").join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSearchUser() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = client.actions().search().searchUser("Kim Kardashian").join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
}
