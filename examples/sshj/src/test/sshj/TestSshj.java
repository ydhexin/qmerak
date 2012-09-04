package test.sshj;

import java.io.IOException;

import org.junit.Test;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.userauth.UserAuthException;

public class TestSshj {
	@Test
	public void testFirst() {
		SSHClient client = new SSHClient();
		try {
			client.loadKnownHosts();

			client.connect("master");
			String userString = System.getProperty("user.name");
			System.out.println(userString);
			client.authPublickey(userString);

			Session session = client.startSession();
			Command command = session.exec("ll");
		} catch (UserAuthException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
