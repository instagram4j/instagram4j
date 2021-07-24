package instagram4j;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.utils.SerializeUtil;

public class SerializeTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testProfile() throws IOException, ClassNotFoundException {
        Profile profile = new Profile();
        profile.setFull_name("Test name");
        profile.setPk(1234L);
        profile.set_private(false);
        profile.setUsername("username");
        profile.setHas_anonymous_profile_picture(true);
        profile.setProfile_pic_id("test");
        profile.setProfile_pic_url("test");
        profile.set_verified(true);
        File file = File.createTempFile("profile", ".ser");
        file.deleteOnExit();
        SerializeUtil.serialize(profile, file);
        Profile deserializedProfile = SerializeUtil.deserialize(file, Profile.class);

        Assert.assertEquals(profile, deserializedProfile);
    }

    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testIGClient() throws IOException, ClassNotFoundException {
        IGClient client = IGClient.builder().username("username").password("password").build();
        File clientFile = File.createTempFile("client", ".ser");
        File cookieFile = File.createTempFile("cookie", ".ser");
        clientFile.deleteOnExit();
        cookieFile.deleteOnExit();
        client.serialize(clientFile, cookieFile);
        IGClient deserializedClientFrom = IGClient.deserialize(clientFile, cookieFile);
        Assert.assertNotNull(deserializedClientFrom.getHttpClient());
        Assert.assertEquals(client, deserializedClientFrom);
    }

}
