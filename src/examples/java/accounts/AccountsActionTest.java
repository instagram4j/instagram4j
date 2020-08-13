package accounts;

import java.io.File;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsActionRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsActionRequest.AccountsAction;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsChangeProfilePictureRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsCurrentUserRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsEditProfileRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsEditProfileRequest.AccountsEditProfilePayload;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsSetBiographyRequest;
import com.github.instagram4j.instagram4j.requests.upload.RuploadPhotoRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.accounts.AccountsUserResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountsActionTest {
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testCurrent() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        AccountsUserResponse response = new AccountsCurrentUserRequest().execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testEdit() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = new AccountsEditProfileRequest(new AccountsEditProfilePayload("username", "user@email.com").first_name("First name").biography("Cool!")).execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
        log.debug("Success");
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testSetBiography() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = new AccountsSetBiographyRequest("Test boi!").execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testChangeProfilePicture() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        String upload = String.valueOf(System.currentTimeMillis());
        new RuploadPhotoRequest(Files.readAllBytes(new File("src/examples/resources/profile.jpg").toPath()), "1", upload, false).execute(client).join();
        IGResponse response = new AccountsChangeProfilePictureRequest(upload).execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
    }
    
    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testAction() throws Exception {
        IGClient client = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        IGResponse response = new AccountsActionRequest(AccountsAction.SET_PRIVATE).execute(client).join();
        Assert.assertEquals("ok", response.getStatus());
    }
}
