package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

@RunWith(JUnitParamsRunner.class)
@Slf4j
public class SerializeTestUtil {
    @Test
    @FileParameters("src/examples/resources/login.csv")
    public void serializeLogin(String username, String password)
            throws IGLoginException, IGResponseException, ClassNotFoundException,
            FileNotFoundException, IOException {
        File to = new File("src/examples/resources/igclient.ser"),
                cookFile = new File("src/examples/resources/cookie.ser");
        SerializableCookieJar jar = new SerializableCookieJar();
        IGClient lib = new IGClient.Builder().username(username).password(password)
                .client(formTestHttpClient(jar))
                .onLogin(lr -> Assert.assertEquals("ok", lr.getStatus()))
                .login();
        log.debug("Serializing. . .");
        serialize(lib, to);
        serialize(jar, cookFile);
        log.debug("Deserializing. . .");
        IGClient saved = IGClient.from(new FileInputStream(to),
                formTestHttpClient(deserialize(cookFile, SerializableCookieJar.class)));
        log.debug(lib.toString());
        log.debug(saved.toString());
        Assert.assertTrue(saved.equals(lib));

    }

    // logging interceptor
    private static final HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor((msg) -> {
                log.debug(msg);
            }).setLevel(Level.BODY);

    public static IGClient getClientFromSerialize(String clientFile, String cookieFile)
            throws ClassNotFoundException, FileNotFoundException, IOException {
        File to = new File("src/examples/resources/" + clientFile),
                cookFile = new File("src/examples/resources/" + cookieFile);
        InputStream fileIn = new FileInputStream(to);
        IGClient client = IGClient.from(fileIn,
                formTestHttpClient(deserialize(cookFile, SerializableCookieJar.class)));
        fileIn.close();

        return client;
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

    public static OkHttpClient formTestHttpClient() {
        return IGUtils.defaultHttpClientBuilder().addInterceptor(loggingInterceptor)
                .build();
    }

    public static OkHttpClient formTestHttpClient(SerializableCookieJar jar) {
        return IGUtils.defaultHttpClientBuilder().cookieJar(jar)
                .addInterceptor(loggingInterceptor).build();
    }
}
