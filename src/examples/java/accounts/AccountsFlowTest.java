package accounts;

import java.util.concurrent.CompletableFuture;

import org.junit.Assert;
import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class AccountsFlowTest {
    @Test
    public void testPreLoginFlow() {
        IGClient client = new IGClient("", "", SerializeTestUtil.formTestHttpClient());
        client.actions().simulate().preLoginFlow().stream()
                .map(CompletableFuture::join)
                .map(IGResponse.class::cast)
                .forEach(res -> {
                    log.info("{} : {}", res.getClass().getName(), res.getStatus());
                    Assert.assertEquals("ok", res.getStatus());
                });
        log.info("Success");
    }

    @Test
    public void testPostLoginFlow() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        long start = System.currentTimeMillis();
        client.actions().simulate().postLoginFlow().parallelStream()
                .map(CompletableFuture::join)
                .map(IGResponse.class::cast)
                .forEach(res -> {
                    log.info("{} : {}", res.getClass().getName(), res.getStatus());

                    Assert.assertEquals("ok", res.getStatus());
                });
        log.info("Success Took {}", System.currentTimeMillis() - start);
    }
}
