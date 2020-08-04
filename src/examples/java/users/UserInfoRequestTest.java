package users;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import serialize.SerializeTestUtil;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;
import com.github.instagram4j.instagram4j.requests.users.UsersInfoRequest;
import com.github.instagram4j.instagram4j.requests.users.UsersUsernameInfoRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

public class UserInfoRequestTest {
    @Test
    public void testLogin()
            throws IGLoginException, IGResponseException, ClassNotFoundException, FileNotFoundException, IOException {
        IGClient lib = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        UsersUsernameInfoRequest req = new UsersUsernameInfoRequest("seattlegoldgrills");
        UsersInfoRequest req2 = new UsersInfoRequest(18428658l);
        IGResponse response = lib.sendRequest(req), res = lib.sendRequest(req2);
        Assert.assertEquals("ok", response.getStatus());
        Assert.assertEquals("ok", res.getStatus());
    }
}
