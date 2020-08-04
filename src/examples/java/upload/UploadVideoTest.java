package upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureTimelineRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureTimelineRequest.MediaConfigurePayload;
import com.github.instagram4j.instagram4j.requests.upload.MediaUploadFinishRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadPhotoRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadVideoRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureTimelineResponse;

public class UploadVideoTest {
    @Test
    public void uploadTest()
            throws IGLoginException, IOException, IGResponseException, ClassNotFoundException {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        File videoFile = new File("src/examples/resources/test.mp4");
        File thumbnail = new File("src/examples/resources/test.jpg");
        byte[] videoData = Files.readAllBytes(videoFile.toPath());
        Long duration = 13l;
        byte[] imgData = Files.readAllBytes(thumbnail.toPath());
        BufferedImage img = ImageIO.read(thumbnail);
        String uploadId = String.valueOf(System.currentTimeMillis());
        IGRequest<IGResponse> uploadReq = new RuploadVideoRequest(videoData,
                UploadParameters.forTimelineVideo(uploadId, false));
        IGRequest<?> uploadThumbReq = new RuploadPhotoRequest(imgData, "1", uploadId, false);
        IGRequest<?> finish = new MediaUploadFinishRequest(uploadId);
        IGResponse uploadResponse = client.sendRequest(uploadReq);
        IGResponse uploadTResponse = client.sendRequest(uploadThumbReq);
        IGResponse finishResponse = client.sendRequest(finish);

        Assert.assertEquals("ok", uploadResponse.getStatus());

        IGRequest<MediaConfigureTimelineResponse> configReq = new MediaConfigureTimelineRequest(new MediaConfigurePayload().upload_id(uploadId).caption("test video"));
        IGResponse response = client.sendRequest(configReq);

        Assert.assertEquals("ok", response.getStatus());
    }
}