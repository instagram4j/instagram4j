package login;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Callable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.IGClient.Builder.LoginHandler;
import com.github.instagram4j.instagram4j.utils.IGChallengeUtils;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ChallengeLoginTest {
    @Test
    @FileParameters("src/examples/resources/login.csv")
    public void testChallengeLogin(String username, String password) throws IOException {
        Scanner scanner = new Scanner(System.in);

         // Callable that returns inputted code from System.in
         Callable<String> inputCode = () -> {
             System.out.print("Please input code: ");
             return scanner.nextLine();
         };
    
         // handler for challenge login
         LoginHandler challengeHandler = (client, response) -> {
             // included utility to resolve challenges
             // may specify retries. default is 3
             return IGChallengeUtils.resolveChallenge(client, response, inputCode);
         };
    
         IGClient client = IGClient.builder()
                 .username(username)
                 .password(password)
                 .onChallenge(challengeHandler)
                 .login();
         
        Assert.assertNotNull(client.getSelfProfile());
    }
}
