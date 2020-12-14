package actions;

import org.junit.Assert;
import org.junit.Test;
import com.github.instagram4j.instagram4j.IGClient;
import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class SearchActionTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSearchTag() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        client.actions().search().searchTag("love")
        .thenAccept(res -> {
            Assert.assertEquals("ok", res);
            res.getResults().forEach(tag -> {
                log.debug(tag.getId() + " " + tag.getMedia_count());
            });
        }).join();
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSearchLocation() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        client.actions().search().searchPlace("San Francisco").thenAccept(res -> {
            Assert.assertEquals("ok", res.getStatus());
            res.getItems().forEach(item -> {
                log.debug(item.getTitle());
            });
        }).join();
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSearchUser() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        client.actions().search().searchUser("Kim Kardashian")
        .thenAccept(res -> {
            Assert.assertEquals("ok", res.getStatus());
            res.getUsers().forEach(user -> {
                log.debug(user.getFull_name());
            });
        }).join();
        log.debug("Success");
    }
}
