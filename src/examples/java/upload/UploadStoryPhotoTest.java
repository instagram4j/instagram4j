package upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.models.media.reel.item.StoryCountdownsItem;
import com.github.instagram4j.instagram4j.models.media.reel.item.StoryLocationsItem;
import com.github.instagram4j.instagram4j.models.media.reel.item.StoryPollsItem;
import com.github.instagram4j.instagram4j.models.media.reel.item.StoryQuestionsItem;
import com.github.instagram4j.instagram4j.models.media.reel.item.StoryQuizItems;
import com.github.instagram4j.instagram4j.models.media.reel.item.StoryQuizItems.Option;
import com.github.instagram4j.instagram4j.models.media.reel.item.StorySlidersItem;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureToStoryRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadPhotoRequest;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureToStoryResponse;
import com.github.instagram4j.instagram4j.responses.media.RuploadPhotoResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadStoryPhotoTest {
    @Test
    public void uploadTest()
            throws IGLoginException, IOException, IGResponseException, ClassNotFoundException {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        File file = new File("src/examples/resources/story.jpg");
        byte[] imgData = Files.readAllBytes(file.toPath());
        // some items
        StoryQuestionsItem qSticker = StoryQuestionsItem.builder().question("Did it work?")
                .profile_pic_url(client.getSelfProfile().getProfile_pic_url()).build();
        StoryPollsItem qPolls = StoryPollsItem.builder().y(0.8).question("Select one.").build();
        StorySlidersItem qSlides = StorySlidersItem.builder().question("Cool?").build();
        StoryCountdownsItem qCountdown = StoryCountdownsItem.builder().text("Until")
                .end_ts((System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)) / 1000).following_enabled(false)
                .build();
        List<Option> options = Arrays.asList(Option.builder().text("1").build(),
                Option.builder().text("2").build(), Option.builder().text("3").build(),
                Option.builder().text("4").build());
        StoryQuizItems qQuiz = StoryQuizItems.builder().question("Which?").options(options).correct_answer(2)
                .build();
        StoryLocationsItem locQ = StoryLocationsItem.builder().location_id("106048159426257").build();
        // end items
        IGRequest<RuploadPhotoResponse> uploadReq = new RuploadPhotoRequest(imgData, "1");
        String id = client.sendRequest(uploadReq).getUpload_id();
        MediaConfigureToStoryRequest configReq = new MediaConfigureToStoryRequest(id, Arrays.asList(qSticker, qPolls));
        MediaConfigureToStoryResponse response = client.sendRequest(configReq);
        
        Assert.assertEquals("ok", response.getStatus());
        log.debug(response.getMedia().getId());
        log.debug(response.getMedia().get("story_questions").toString());
    }
}
