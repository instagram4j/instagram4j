import java.util.Scanner;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.exceptions.IGChallengeException;
import com.github.instagram4j.Instagram4J.exceptions.IGLoginException;

public class ExampleLoginChallenge {
	public static void main(String[] args) throws IGLoginException, IGChallengeException {
		if (args.length != 2) {
			System.err.println();
			System.exit(1);
		}
		
		String user = args[0], pass = args[1];
		IGClient igClient = new IGClient.Builder().withUsername(user).withPassword(pass).withOnTwoFactor((client, response) -> {
			String identifier = response.getTwo_factor_info().getTwo_factor_identifier();
			Scanner in = new Scanner(System.in);
			System.out.println("Please enter two factor code for " + identifier + ":");
			String code = in.next();
			in.close();
			
			return client.sendLoginRequest(code, identifier);
		}).login();
		
		System.out.println("Logged into : " + client.getSelfUser().getFull_name());
	}
}
