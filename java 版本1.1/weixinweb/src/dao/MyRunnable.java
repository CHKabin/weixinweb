package dao;



public class MyRunnable implements Runnable{

	public String corpid = null;
	//	应用的凭证密钥
	public String corpsecret = null;
	
	//授权方应用id
	public String agentid = null;
	
	//发送内容
	public String content = null;
	//发送日期
	public String contentdate = null;
	
    public MyRunnable(String corpid, String corpsecret, String agentid, String content, String contentdate) {
		this.corpid = corpid;
		this.corpsecret = corpsecret;
		this.agentid = agentid;
		this.content = content;
		this.contentdate = contentdate;
    }
 
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("子线程ID："+Thread.currentThread().getId());
		Httpclient http = new Httpclient(corpid,corpsecret,agentid,content,contentdate);
		http.weixinhttp();	
	}

}
