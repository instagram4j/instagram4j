package serialize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
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
        IGClient lib = new IGClient.Builder().username(username).password(password)
                .onLogin((cli, lr) -> Assert.assertEquals("ok", lr.getStatus()))
                .login();
        log.debug("Serializing. . .");
        lib.serialize(to, cookFile);
        log.debug("Deserializing. . .");
        IGClient saved = IGClient.deserialize(to, cookFile, formTestHttpClientBuilder());
        log.debug(lib.toString());
        log.debug(saved.toString());
        Assert.assertTrue(saved.equals(lib));

    }
    
    public static IGClient getClientFromSerialize(String client, String cookie) throws ClassNotFoundException, IOException {
        File to = new File("src/examples/resources/" + client),
                cookFile = new File("src/examples/resources/" + cookie);
        
        return IGClient.deserialize(to, cookFile, formTestHttpClientBuilder());
    }

    // logging interceptor
    private static final HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor((msg) -> {
                log.debug(msg);
            }).setLevel(Level.BODY);

    public static OkHttpClient.Builder formTestHttpClientBuilder() {
        return IGUtils.defaultHttpClientBuilder().addInterceptor(loggingInterceptor);
    }
}
