package example;


import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * 远程连接 工具类
 * 
 * @author ydhexin@163.com
 * 
 */
public class SshUtil {

	/**
	 * 获取一个ssh 连接,没有使用key文件,直接使用用户名和密码 使用默认SSH端口22
	 * 
	 * @param host
	 * @param password
	 * @param username
	 * @param port
	 * @return
	 */
	public static Session openSession(String host, String username, String password) {
		return openSession(host, username, password, 22);
	}

	/**
	 * 获取一个ssh 连接,没有使用key文件,直接使用用户名和密码
	 * 
	 * @param host
	 * @param password
	 * @param username
	 * @param port
	 * @return
	 */
	public static Session openSession(String host, String username, String password, int port) {
		JSch jsch = new JSch();
		Session session = null;
		try {
			session = jsch.getSession(username, host, port);
			session.setPassword(password);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
		} catch (JSchException e) {
			throw new RuntimeException(e);
		}
		return session;
	}

	

	/**
	 * 获取一个可以直接执行命令的连接,感觉不能关闭session,是不是会出现问题.所有先把session放到下层
	 * 
	 * @param host
	 * @param password
	 * @param username
	 * @param port
	 * @return
	 */
	@Deprecated
	public static ChannelExec exec(String host, String username, String password, int port) {
		ChannelExec exec = null;
		try {
			Session session = openSession(host, username, password, port);
			exec = (ChannelExec) session.openChannel("exec");
		} catch (JSchException e) {
			throw new RuntimeException(e);
		}
		return exec;
	}

	/**
	 * 获取一个可以直接执行命令的连接，使用默认SSH端口22
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * @return
	 */
	@Deprecated
	public static ChannelExec exec(String host, String username, String password) {
		return exec(host, username, password, 22);
	}
}
