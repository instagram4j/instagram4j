
import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestTemplate {
    @Test
    public void testName() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = null;
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
}
