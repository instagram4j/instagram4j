package upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.media.UploadParameters;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureToIgtvRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadPhotoRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadSegmentVideoGetRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadSegmentVideoPhaseRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadSegmentVideoPhaseRequest.Phase;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadToIgtvTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testName() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        File videoFile = new File("src/example/resources/igtvvideo.mp4"),
                cover = new File("src/examples/resources/igtvphoto.jpg");
        String upload_id = String.valueOf(System.currentTimeMillis());
        byte[] data = Files.readAllBytes(videoFile.toPath());
        uploadSegments(client, upload_id, segments(data, 10_000_000), data.length);
        new RuploadPhotoRequest(Files.readAllBytes(cover.toPath()), "2", upload_id, false).execute(client).join();
        int i = 0;
        do {
            MediaConfigureToIgtvRequest config = new MediaConfigureToIgtvRequest(upload_id, "Goober", "Wow!");
            IGResponse response = config.execute(client).join();
            if (response.getStatus().equals("fail")) {
                log.debug("waiting");
                Thread.sleep(1000 * 10 * (i+1));
            } else { i = 4; }
        } while (i++ < 3);
        log.debug("{}", i >= 3 ? "Success" : "fail");
    }

    public static void uploadSegments(IGClient client, String upload_id, byte[][] segments, int totalLength) throws IOException {
        String transfer_id = "igtv_" + upload_id;
        UploadParameters parameter = UploadParameters.forIgtv(upload_id);
        RuploadSegmentVideoPhaseRequest start = new RuploadSegmentVideoPhaseRequest(Phase.START, parameter), end;
        String stream_id = start.execute(client).join().get("stream_id").toString();
        
        for (int i = 0; i < segments.length; i++) {
            String offset = String.valueOf(i * segments[0].length);
            RuploadSegmentVideoGetRequest getReq = new RuploadSegmentVideoGetRequest(parameter, stream_id, transfer_id, offset);
            String getoffset = getReq.execute(client).join().get("offset").toString();
            RuploadSegmentVideoPhaseRequest transfer = new RuploadSegmentVideoPhaseRequest(Phase.TRANSFER, parameter, stream_id, transfer_id, offset, String.valueOf(totalLength), segments[i]);
            log.debug("Uploading: {} of {}", i + 1 + "", segments.length + "");
            transfer.execute(client).join();
            log.debug("Done {}", i + "");
        }
        
        end = new RuploadSegmentVideoPhaseRequest(Phase.END, parameter, stream_id, transfer_id);
        end.execute(client).join();
    }

    public static byte[][] segments(byte[] data, int segmentSize) {
        int remainder = data.length % segmentSize;
        int segments = data.length / segmentSize + (remainder == 0 ? 0 : 1);
        byte[][] ans = new byte[segments][];
        for (int i = 0; i < (remainder == 0 ? segments : segments - 1); i++)
            ans[i] = Arrays.copyOfRange(data, i * segmentSize, i * segmentSize + segmentSize);
        if (remainder != 0)
            ans[ans.length - 1] = Arrays.copyOfRange(data, (ans.length - 1) * segmentSize,
                    (ans.length - 1) * segmentSize + remainder);

        return ans;
    }
}
