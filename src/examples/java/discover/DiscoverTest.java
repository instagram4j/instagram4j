package discover;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.discover.SectionalMediaGridItem;
import com.github.instagram4j.instagram4j.requests.discover.DiscoverTopicalExploreRequest;
import com.github.instagram4j.instagram4j.responses.discover.DiscoverTopicalExploreResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscoverTest {
    @Test
    public void testName() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        DiscoverTopicalExploreResponse response = new DiscoverTopicalExploreRequest().execute(client);
        Assert.assertEquals("ok", response.getStatus());
        response.getSectional_items().forEach(item -> log.debug("{} : {}", item.getLayout_type(), item.getClass().getName()));
        response.getSectional_items().stream().filter(i -> i instanceof SectionalMediaGridItem).map(SectionalMediaGridItem.class::cast).forEach(item -> item.getMedias().forEach(media -> log.debug(media.getId())));
        log.debug(response.getNext_max_id());
        response = new DiscoverTopicalExploreRequest(response.getNext_max_id(), null).execute(client);
        log.debug(response.getNext_max_id());
        log.debug("Success");
    }
}
