/**
 * 
 */
package net.qmerak.webmsg;

import java.util.Map.Entry;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * @author Shaohong•Cheng
 * @mail shaohong726@126.com
 * @date 2012-9-2
 * @project QMerak-WebMSG
 * 
 *          系统主入口，启动Web服务，启动数据库服务，初始化系统
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			for (Entry<String, String> entry : System.getenv().entrySet()) {
				// System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			System.out
					.println("=========================================================================");
			for (Object key : System.getProperties().keySet()) {
				// System.out.println(key + ":"
				// + System.getProperty(String.valueOf(key)));
			}

			Server server = new Server();
			server.addConnector(createDefaultChannelConnector());

			String appPath = System.getProperty("user.dir");
			WebAppContext appContext = new WebAppContext(appPath
					+ "/WebContent", "/");
			server.addHandler(appContext);

			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connector createDefaultChannelConnector() {
		SelectChannelConnector ret = new SelectChannelConnector();
		ret.setLowResourceMaxIdleTime(10000);
		ret.setAcceptQueueSize(128);
		ret.setResolveNames(false);
		ret.setUseDirectBuffers(false);

		ret.setHost("localhost");
		ret.setPort(8889);
		return ret;
	}

}
