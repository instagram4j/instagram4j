package accounts;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.qe.QeSyncRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QeSyncTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testName() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = new QeSyncRequest().execute((client));
        Assert.assertEquals("ok", response.getStatus());
        response.getExtraProperties().keySet().forEach(log::debug);
        log.debug("Success");
    }
}
