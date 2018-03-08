package dao;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;


import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class Httpclient {

	//企业ID
	private String corpid;
	//	应用的凭证密钥
	private String corpsecret;
	
	//授权方应用id
	private String agentid;
	
	//发送内容
	private String content;
	//发送日期
	private String contentdate;
	
	public Httpclient(String corpid, String corpsecret, String agentid, String content, String contentdate){
		this.corpid = corpid;
		this.corpsecret = corpsecret;
		this.agentid = agentid;
		this.content = content;
		this.contentdate = contentdate;
	}

	public void weixinhttp(){
		System.out.println("开始获取 access_token!");
		CloseableHttpClient closehp = HttpClients.createDefault();
		//企业ID
		//corpid = "ww4239a83c07569367";
		//	应用的凭证密钥
		//corpsecret = "M1YvfKeJs4WUUfTGbgwVJ1JYpd4bJgPSysi6NBZboSw";
		
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpid+"&corpsecret="+corpsecret;
		
		HttpGet httpget = new HttpGet(url);	
		//System.out.println(httpget.getURI());
		
		//获取到的凭证
		String access_token ="";
		try {
			CloseableHttpResponse response = closehp.execute(httpget);
			
			if(response.getStatusLine().getStatusCode() == 200){
				String restring = EntityUtils.toString(response.getEntity());
				JSONObject ob = JSONObject.fromObject(restring);
				String errcode = ob.getString("errcode");
				if(errcode.endsWith("0")){
					access_token = ob.getString("access_token");
					System.out.println("已获取 access_token!");
				}
				//System.out.println(EntityUtils.toString(response.getEntity()));
	            
			}
			else{
				System.out.println("已获取 access_token失败!");
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testagentid(access_token);
		
	}
		

	public void testagentid(String access_token){
		
		System.out.println("开始获取需要发送信息的员工列表!");
		
		CloseableHttpClient closehp = HttpClients.createDefault();
		
		//授权方应用id
		//String agentid = "1000002";
		
		String url ="https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token="+access_token+"&agentid="+agentid;
		
		HttpGet httpget = new HttpGet(url);	
		//System.out.println(httpget.getURI());
		String allow_userinfos ="";
		String userstr = "";
		try {
			CloseableHttpResponse response = closehp.execute(httpget);
			
			if(response.getStatusLine().getStatusCode() == 200){
				String restring = EntityUtils.toString(response.getEntity());
				JSONObject ob = JSONObject.fromObject(restring);
				String errcode = ob.getString("errcode");
				if(errcode.endsWith("0")){
					allow_userinfos = ob.getString("allow_userinfos");
					
					JSONArray ob1 = ob.getJSONObject("allow_userinfos").getJSONArray("user");
					int num = ob1.size();
					
					for(int i = 0;i< num ;i++){
						String userid = ob1.getJSONObject(i).getString("userid");
						if(i == 0){
							userstr = userid;
						}else{
							userstr = userstr + "|" + userid ;
						}
						
					}
					System.out.println("已获取需要发送信息的员工列表!列表为:"+userstr);
					
				}
				//System.out.println(EntityUtils.toString(response.getEntity()));
	            
			}
			else{
				System.out.println("false，获取需要发送信息的员工列表失败!");
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendReqMsg(access_token,userstr);
		
	}


	public void testpost(){
		
		//HttpClient httpclient = new DefaultHttpClient();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//String url = "http://localhost:8080/NonResident/userqueryservlet.do";
		//String url = "http://photo.ujiabao.com/TestUpInfors.action";
		String access_token = "";
		String url = " https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+access_token;
		
		//HttpPost httppost = new HttpPost(url);
		
		/*List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        JSONObject jobj = new JSONObject();  
        jobj.put("uid", uid);  
        jobj.put("title", title);  
        jobj.put("content",content);
        
        nameValuePairs.add(new BasicNameValuePair("msg", getStringFromJson(jobj)));  
        httppost.addHeader("Content-type", "application/x-www-form-urlencoded");  
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  */
        
        try {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
            formparams.add(new BasicNameValuePair("investorid", "123456"));  
            formparams.add(new BasicNameValuePair("salesdepartment", "来了来了来了"));  
            formparams.add(new BasicNameValuePair("PageNo", "1"));  
            formparams.add(new BasicNameValuePair("PageSize", "10"));
            formparams.add(new BasicNameValuePair("refresh", "0"));  
            
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);  
            HttpPost httppost = new HttpPost(url); 
            
            httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
            httppost.setEntity(entity); 
        	
			HttpResponse response = httpclient.execute(httppost);
			
			
			if(response.getStatusLine().getStatusCode() == 200 ){
				HttpEntity entity1 = response.getEntity();
				String conResult = null;
				if(entity1 != null){
					conResult = EntityUtils.toString(entity1);
					System.out.println(conResult);
				}
                //String conResult = EntityUtils.toString(response.getEntity()); 
                
                JSONObject jsonobject = JSONObject.fromObject(conResult);
                String total = jsonobject.getString("total");
                //由于 后台servlet  rows写的是 jsonarray，jsonarray里面放入的是 jsonobject，所以按照以下的取
                //先取出jsonarray
                JSONArray jsonoArray = jsonobject.getJSONArray("rows");
                //从jsonarray中取出 jsonobject
                JSONObject jsonobject2=jsonoArray.getJSONObject(0);
                //从jsonobject中取出key
                String userid = jsonobject2.getString("userid");
                String username = jsonobject2.getString("username");
                String identitycard = jsonobject2.getString("identitycard");
                String address = jsonobject2.getString("address");
                
                
                System.out.println(response.getEntity().getContentType());
                System.out.println(total);
                System.out.println(userid);
                System.out.println(username);
                System.out.println(identitycard);
                System.out.println(address);
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
	}

	
	public void jsontest(){
		JSONObject jb = new JSONObject();
		jb.put("nihao", "10");
		JSONArray ja = new JSONArray();
		ja.add(0,"1");
		ja.add(1,"2");
		jb.put("array", ja);
		
		JSONArray array = jb.getJSONArray("array");
		//String c = "{total:1,rows:[{"userid":"123456","username":"test","identitycard":"","address":"","phone":"","equity":"0","noresident":"居民","date":"2017-07-18 17:27:53"}]}";
		String c ="{total:1,rows:[1,10,1,2]}";
		JSONObject ob = JSONObject.fromObject(c);
		//System.out.println(c);
		System.out.println(ob);
		
	}
	
	
	/** 
     * 主动发送消息 
     * @param mess 
     * @return 
     */  
    public  boolean sendReqMsg(String token,String userstr){  
    	System.out.println("开始发送信息!");
		JSONObject jb = new JSONObject();
		JSONObject jb1 = new JSONObject();
		content = content + "出现故障，请及时处理 ! " + contentdate;
		jb1.put("content", content);
		
		jb.put("touser", userstr);
		jb.put("toparty", "@all");
		jb.put("totag", "@all");
		jb.put("msgtype", "text");
		jb.put("agentid", agentid);
		jb.put("text", jb1);
		jb.put("safe", "0");
		
		
		String c = jb.toString();
		
		//System.out.println(c);
    	
        //消息json格式  
        String jsonContext=c;  
        //System.out.println(jsonContext);  
        //获得token  
        //String token="kCtACpTjqzsff2XE1U8173445Uy8MYcjFjaGlgxmRk6O6CkOtxqybuMiWgnIR-jsGI3q5V1cgRqAI7Bnn2JmO736rv4fsdB54zVq4v-nAZrhSuLWjoVlElF64U3jpG7VVjkRyfaiaR9NwCrs9S2AgXE4Cowr1_3A6_W3d61UFpRqm9Eyu8TFngKl2nAFwWG6k4bpJiHW1a0Pue8j8iVPlOLCGzs7x4jkf9zfrjvYFUQczdQSHKX1y_dBiW3wOQp8cqc3v40CxGjPTUigyn1Ty5ya8kwzTRER7m5fFaKt7n8";  
         boolean flag=false;  
         try {  
             CloseableHttpClient httpclient = HttpClients.createDefault();  
             HttpPost httpPost= new HttpPost("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+token);  
             //发送json格式的数据  
             StringEntity myEntity = new StringEntity(jsonContext,   
                       ContentType.create("text/plain", "UTF-8"));  
             //设置需要传递的数据  
             httpPost.setEntity(myEntity);  
             // Create a custom response handler  
            ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>() {  
                //对访问结果进行处理  
                public JSONObject handleResponse(  
                        final HttpResponse response) throws ClientProtocolException, IOException {  
                    int status = response.getStatusLine().getStatusCode();  
                    if (status >= 200 && status < 300) {  
                        HttpEntity entity = response.getEntity();  
                        if(null!=entity){  
                            String result= EntityUtils.toString(entity);  
                            //根据字符串生成JSON对象  
                            JSONObject resultObj = JSONObject.fromObject(result);  
                            return resultObj;  
                        }else{  
                            return null;  
                        }  
                    } else {  
                        throw new ClientProtocolException("Unexpected response status: " + status);  
                    }  
                }  
  
            };  
          //返回的json对象  
            JSONObject responseBody = httpclient.execute(httpPost, responseHandler);  
            System.out.println("responseBody:"+responseBody);         
            
            
            int result= (Integer) responseBody.get("errcode");  
            if(0==result){  
                flag=true;  
                System.out.println("发送信息完毕"); 
            }else{  
                flag=false; 
                System.out.println("发送信息失败"); 
            }  
            httpclient.close();  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
         return true;  
    }
	
}