package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UDPReceive;

/**
 * WangYanfeng 作者 E-mail:wangyanfeng_2012@163.com
 * 创建时间：2015年2月5日 下午3:09:33
 * 类说明
 */
public class ServerController extends HttpServlet {

	public static boolean isServerOn=false;
	public static Runnable runnable=null;
	public static ExecutorService executorService;
	public ServerController() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		request.setAttribute("isServerOn", isServerOn);
		RequestDispatcher dispatcher=request.getRequestDispatcher("./udpServer.jsp");
		dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String run=request.getParameter("run");
		if(run!=null&&run.equals("0")){
			//关闭服务器
			executorService=Executors.newSingleThreadExecutor();
			if(runnable!=null){
				executorService.execute(runnable);
			}
			executorService.shutdown();
			while(isServerOn!=false){
				
			}
			request.setAttribute("isServerOn", isServerOn);
			RequestDispatcher dispatcher=request.getRequestDispatcher("./udpServer.jsp");
			dispatcher.forward(request, response);
		}else if(run!=null&&run.equals("1")){
			if(!isServerOn){
				//启动服务器
				executorService=Executors.newSingleThreadExecutor();
				if(runnable!=null){
					executorService.execute(runnable);
				}
				while(isServerOn!=true){
					
				}
				request.setAttribute("isServerOn", isServerOn);
				RequestDispatcher dispatcher=request.getRequestDispatcher("./udpServer.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	public void init() throws ServletException {
		// Put your code here
		runnable=new UDPReceive();
	}

}
