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
public class TwoFactorLoginTest {
    @Test
    @FileParameters("src/examples/resources/login.csv")
    public void testTwoFactor(String user, String pass) throws IOException {
        Scanner scanner = new Scanner(System.in);

         // Callable that returns inputted code from System.in
         Callable<String> inputCode = () -> {
             System.out.print("Please input code: ");
             return scanner.nextLine();
         };
    
         // handler for two factor login
         LoginHandler twoFactorHandler = (client, response) -> {
             // included utility to resolve two factor
             return IGChallengeUtils.resolveTwoFactor(client, response, inputCode);
         };
    
         IGClient client = IGClient.builder()
                 .username(user)
                 .password(pass)
                 .onTwoFactor(twoFactorHandler)
                 .login();

        Assert.assertNotNull(client.getSelfProfile());
    }
}
