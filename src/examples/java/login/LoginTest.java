package login;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsCurrentUserRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import lombok.extern.slf4j.Slf4j;
import serialize.SerializeTestUtil;

@Slf4j
@RunWith(JUnitParamsRunner.class)
public class LoginTest {
    @Test
    @FileParameters("src/examples/resources/login.csv")
    public void testName(String username, String password)
            throws Exception {
        IGClient client = IGClient.builder()
                .username(username)
                .password(password)
                .client(SerializeTestUtil.formTestHttpClientBuilder().build())
                .onLogin((cli, response) -> {
                    cli.sendRequest(new AccountsCurrentUserRequest())
                    .thenAccept(repsonse -> {
                        Assert.assertThat(response.getStatus(), CoreMatchers.is("ok"));
                    })
                    .join();
                })
                .login();
        log.debug(client.toString());
        Assert.assertNotNull(client.getSelfProfile());
        log.debug("Success");
    }

    public static void postLoginResponsesHandler(List<CompletableFuture<?>> responses) {
        responses.stream()
                .map(res -> res.thenApply(IGResponse.class::cast))
                .forEach(res -> {
                    res.thenAccept(igRes -> {
                        log.info("{} : {}", igRes.getClass().getName(), igRes.getStatus());
                    });
                });
    }
}
