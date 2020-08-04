package login;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import lombok.extern.slf4j.Slf4j;

@Slf4j@RunWith(JUnitParamsRunner.class)
public class LoginTest {
    @Test
    @FileParameters("src/examples/resources/login.csv")
    public void testName(String username, String password)
            throws Exception {
        IGClient client = IGClient.builder().username(username).password(password).client(SerializeTestUtil.formTestHttpClient()).login();
        log.debug(client.toString());
        Assert.assertNotNull(client.getSelfProfile());
        log.debug("Success");
    }
}
