/**
 * 
 */
package net.qmerak.webmsg.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.util.ajax.Continuation;
import org.mortbay.util.ajax.ContinuationSupport;

/**
 * @author Shaohong•Cheng
 * @mail shaohong726@126.com
 * @date 2012-9-2
 * @project QMerak-WebMSG
 */
public class ContinuationServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5340872109563974395L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("&&&&&:" + request.getParameter("num"));
			Continuation continuation = ContinuationSupport.getContinuation(
					request, null);
			continuation.suspend(3000);
			response.getWriter().write("11");
			System.out.println("response:");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
