package dao;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//��ҵID
		String corpid = "ww4239a83c07569367";
		//	Ӧ�õ�ƾ֤��Կ
		String corpsecret = "qmNyjQYNyZXg-5Ns3H6Xk9SmDGD9VUPwqpG_c5ZfJ0k";
		
		//��Ȩ��Ӧ��id
		String agentid = "1000003";
		
		//��������
		String content = args[0];
		//��������
		String contentdate = args[1];
		
/*		//��������
		String content = "niaho";
		//��������
		String contentdate = "2017";*/
		
		//String corpid, String corpsecret, String agentid, String content, String contentdate
		
        System.out.println("���߳�ID��"+Thread.currentThread().getId());
        MyRunnable runnable = new MyRunnable(corpid,corpsecret,agentid,content,contentdate);
        Thread thread = new Thread(runnable);
/*        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);*/
        thread.start();
/*        thread2.start();
        thread3.start();
        thread4.start();*/
		
/*		Httpclient http = new Httpclient(corpid,corpsecret,agentid,content,contentdate);
		http.weixinhttp();*/
		
		
		
	}

}
