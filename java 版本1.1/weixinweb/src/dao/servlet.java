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
		
		//���÷������˱��� Ϊ UTF-8 ���ַ���������
		//response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//post ��������ҳ����������ݱ��� 
		//request.setCharacterEncoding("utf-8");
		
		//��ҵID
		String corpid = request.getParameter("corpid");
//		Ӧ�õ�ƾ֤��Կ
		String corpsecret = request.getParameter("corpsecret");
		//��Ȩ��Ӧ��id
		String agentid = request.getParameter("agentid");
		//��������  ת�� ��Ȼ����������
		String content =  new String(request.getParameter("content").getBytes("iso-8859-1"), "utf-8");
		//String content = request.getParameter("content");
		
		System.out.println(content);
		//��������
		String contentdate = request.getParameter("contentdate");
		
		//ת�� ��Ȼ����������
	    //String salesdepartment = new String(request.getParameter("salesdepartment").getBytes("iso-8859-1"), "utf-8");  
		
/*		//��ҵID
		String corpid = "ww4239a83c07569367";
		//	Ӧ�õ�ƾ֤��Կ
		String corpsecret = "qmNyjQYNyZXg-5Ns3H6Xk9SmDGD9VUPwqpG_c5ZfJ0k";
		
		//��Ȩ��Ӧ��id
		String agentid = "1000003";
		
		//��������
		String content = "niaho";
		//��������
		String contentdate = "2017";*/
		
        System.out.println("���߳�ID��"+Thread.currentThread().getId());
        
        MyRunnable runnable = new MyRunnable(corpid,corpsecret,agentid,content,contentdate);
        Thread thread = new Thread(runnable);
        thread.start();

	}
		
}
