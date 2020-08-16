package instagram4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.user.Profile;

import lombok.SneakyThrows;

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
        serialize(profile, file);
        Profile deserializedProfile = deserialize(file, Profile.class);

        Assert.assertEquals(profile, deserializedProfile);
    }

    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testIGClient() throws IOException, ClassNotFoundException {
        IGClient client = IGClient.builder().username("username").password("password").build();
        File file = File.createTempFile("client", ".ser");
        file.deleteOnExit();
        serialize(client, file);
        IGClient deserializedClientFrom = IGClient.from(new FileInputStream(file));
        Assert.assertNotNull(deserializedClientFrom.getHttpClient());
        Assert.assertEquals(client, deserializedClientFrom);
    }

    @SneakyThrows
    public static void serialize(Object o, File to) {
        FileOutputStream file = new FileOutputStream(to);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(o);
        out.close();
        file.close();
    }

    @SneakyThrows
    public static <T> T deserialize(File file, Class<T> clazz) {
        InputStream in = new FileInputStream(file);
        ObjectInputStream oIn = new ObjectInputStream(in);

        T t = clazz.cast(oIn.readObject());

        in.close();
        oIn.close();

        return t;
    }

}
