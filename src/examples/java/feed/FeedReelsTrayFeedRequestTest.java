package feed;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.responses.feed.FeedReelsTrayResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
public class FeedReelsTrayFeedRequestTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testFeedRequest()
            throws IGResponseException, IGLoginException, ClassNotFoundException, FileNotFoundException, IOException {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        FeedReelsTrayResponse response = client.actions().story().tray().join();
        Assert.assertEquals("ok", response.getStatus());
        
        /* response.getTray().forEach(tray -> {
            tray.getItems().stream()
            .map(x -> x.get("story_cta"))
            .filter(Objects::nonNull)
            .forEach(story_cta -> {
                ArrayNode node = IGUtils.convertToView(story_cta, ArrayNode.class);
                log.info(node.get(0).get("links").get(0).get("webUri").asText());
            });
        }); */
        
        response.getTray().forEach(tray -> {
            tray.getItems().stream()
           .map(item -> item.get("story_cta"))
           .filter(Objects::nonNull)
           .flatMap(story_cta -> StoryCta.convert(story_cta).stream())
           .forEach(item -> {
              log.debug(item.getLinks().get(0).getWebUri());
           });
       });
    }
    
    @Data
    public static class StoryCta {
        private List<StoryCtaLink> links;
        
        public static List<StoryCta> convert(Object o) {
            return IGUtils.convertToView(o, new TypeReference<List<StoryCta>>() {});
        }
    }
    
    @Data
    public static class StoryCtaLink {
        private String webUri;
    }
}