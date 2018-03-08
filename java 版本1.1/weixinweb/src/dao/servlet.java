package dao;


import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class servlet extends HttpServlet{

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		//设置服务器端编码 为 UTF-8 两种方法都可以
		//response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//post 设置来自页面的请求数据编码 
		//request.setCharacterEncoding("utf-8");
		
		//企业ID
		String corpid = request.getParameter("corpid");
//		应用的凭证密钥
		String corpsecret = request.getParameter("corpsecret");
		//授权方应用id
		String agentid = request.getParameter("agentid");
		//发送内容  转码 不然中文是乱码
		String content =  new String(request.getParameter("content").getBytes("iso-8859-1"), "utf-8");
		//String content = request.getParameter("content");
		
		System.out.println(content);
		//发送日期
		String contentdate = request.getParameter("contentdate");
		
		//转码 不然中文是乱码
	    //String salesdepartment = new String(request.getParameter("salesdepartment").getBytes("iso-8859-1"), "utf-8");  
		
/*		//企业ID
		String corpid = "ww4239a83c07569367";
		//	应用的凭证密钥
		String corpsecret = "qmNyjQYNyZXg-5Ns3H6Xk9SmDGD9VUPwqpG_c5ZfJ0k";
		
		//授权方应用id
		String agentid = "1000003";
		
		//发送内容
		String content = "niaho";
		//发送日期
		String contentdate = "2017";*/
		
        System.out.println("主线程ID："+Thread.currentThread().getId());
        
        MyRunnable runnable = new MyRunnable(corpid,corpsecret,agentid,content,contentdate);
        Thread thread = new Thread(runnable);
        thread.start();

	}
		
}
