package test.jsch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import org.junit.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.RequestSftp;
import com.jcraft.jsch.Session;

public class TestJsch {

	@Test
	public void testFirst() {
		try {

			// for (Entry<Object, Object> entry : System.getProperties()
			// .entrySet()) {
			// System.out.println(entry.getKey() + ":"
			// + System.getProperty(String.valueOf(entry.getKey())));
			// }

			JSch jSch = new JSch();
			jSch.addIdentity(System.getProperty("user.home") + "/.ssh/id_rsa");
			Session session = jSch.getSession("root", "master2");
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand("pwd");

			channel.setInputStream(null);

			InputStream inputStream = channel.getInputStream();
			channel.connect();

			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String lineString = reader.readLine();
			while (lineString != null) {
				System.out.println(lineString);
				lineString = reader.readLine();
			}

			channel.disconnect();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRequestSftp() {
	}
}
