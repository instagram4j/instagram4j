import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.exceptions.IGChallengeException;
import com.github.instagram4j.Instagram4J.exceptions.IGLoginException;

public class ExampleLogin {
	public static void main(String[] args) throws IGLoginException, IGChallengeException {
		if (args.length != 2) {
			System.err.println("Need username and password arguments");
			System.exit(1);
		}
		String user = args[0], pass = args[1];
		IGClient client = IGClient.builder().withUsername(user).withPassword(pass).login();
		System.out.println("Logged into : " + client.getSelfUser().getFull_name());
	}
}
