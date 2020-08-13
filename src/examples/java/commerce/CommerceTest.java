package commerce;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.commerce.CommerceDestinationRequest;
import com.github.instagram4j.instagram4j.requests.commerce.CommerceProductsDetailsRequest;
import com.github.instagram4j.instagram4j.responses.commerce.CommerceDestinationResponse;
import com.github.instagram4j.instagram4j.responses.commerce.CommerceProductsDetailsResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommerceTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testDestination() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        CommerceDestinationResponse response = new CommerceDestinationRequest().execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
        response.getSectional_items().stream().map(item -> item.getMedias()).forEach(media -> media.forEach(item -> log.debug(item.getId())));
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testProductDetails() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        CommerceProductsDetailsResponse response = new CommerceProductsDetailsRequest("2562765907136094", "7734459462").execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug(response.getProduct_item().getName());
        log.debug("Success");
    }
}
