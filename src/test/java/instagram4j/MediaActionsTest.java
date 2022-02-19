package instagram4j;


import com.github.instagram4j.instagram4j.IGClient;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class MediaActionsTest {
    static final Logger LOGGER = Logger.getRootLogger();
    static final String PATH = "temp/";
    static {
        BasicConfigurator.configure();
        LOGGER.setLevel(Level.ALL);
    }

    @Test
    public void testClipUpload() throws IOException, ClassNotFoundException {
        IGClient client = IGClient.deserialize(new File(PATH + "igclient.ser"), new File(PATH + "cookie.ser"));
        client.actions().clips()
                .uploadClip(new File(PATH + "test.mp4"),
                            new File(PATH + "test.jpg"),
                            "my caption")
                .join();
    }

    @Test
    public void testTimelineVideoUpload() throws IOException, ClassNotFoundException {
        IGClient client = IGClient.deserialize(new File(PATH + "igclient.ser"), new File(PATH + "cookie.ser"));
        client.actions().timeline()
                .uploadVideo(new File(PATH + "test.mp4"),
                        new File(PATH + "test.jpg"),
                        "my caption")
                .join();
    }
}
