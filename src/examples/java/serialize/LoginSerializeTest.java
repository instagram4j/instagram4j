package serialize;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.exceptions.IGResponseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSerializeTest {
    @Test
    public void testName()
            throws IGLoginException, IGResponseException, ClassNotFoundException,
            FileNotFoundException, IOException {
        IGClient lib = SerializeTestUtil.getClientFromSerialize("igclient.ser", "cookie.ser");
        log.debug(lib.toString());
    }
}
