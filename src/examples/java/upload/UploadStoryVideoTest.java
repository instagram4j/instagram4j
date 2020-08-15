package upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureToStoryRequest;
import com.github.instagram4j.instagram4j.requests.upload.MediaUploadFinishRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadPhotoRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadVideoRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureToStoryResponse;

import junitparams.JUnitParamsRunner;
import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@RunWith(JUnitParamsRunner.class)
@Slf4j
public class UploadStoryVideoTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void uploadTest()
            throws IGLoginException, IOException, IGResponseException, ClassNotFoundException {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        File vidFile = new File("src/examples/resources/storyvid.mp4"), covFile = new File("src/examples/resources/story.jpg");
        byte[] vidData = Files.readAllBytes(vidFile.toPath()), covData = Files.readAllBytes(covFile.toPath());
        BufferedImage img = ImageIO.read(covFile);
        String upload_id = String.valueOf(System.currentTimeMillis());
        Long duration = 13l;
        IGRequest<IGResponse> uploadVidReq = new RuploadVideoRequest(vidData,
                UploadParameters.forAlbumVideo(upload_id));
        IGRequest<?> uploadCovReq = new RuploadPhotoRequest(covData, "1", upload_id, false);
        IGRequest<?> finish = new MediaUploadFinishRequest(upload_id);
        IGResponse uploadResponse = client.sendRequest(uploadVidReq).join();
        IGResponse uploadTResponse = client.sendRequest(uploadCovReq).join();
        IGResponse finishResponse = client.sendRequest(finish).join();

        Assert.assertEquals("ok", uploadResponse.getStatus());
        Assert.assertEquals("ok", uploadTResponse.getStatus());
        Assert.assertEquals("ok", finishResponse.getStatus());

        IGRequest<MediaConfigureToStoryResponse> configReq = new MediaConfigureToStoryRequest(upload_id);

        MediaConfigureToStoryResponse response = client.sendRequest(configReq).join();

        log.debug(response.getMedia().getClass().toString());
        Assert.assertEquals("ok", response.getStatus());
    }
}
