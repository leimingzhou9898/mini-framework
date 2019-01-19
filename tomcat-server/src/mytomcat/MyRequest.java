package mytomcat;

import java.io.InputStream;

public class MyRequest {
	private String url;
	private String method;
	public MyRequest(InputStream inputStream) throws Exception {
		String httpRequest="";
		byte[] httpRequestBytes=new byte[1024];
		int length=0;
		if((length=inputStream.read(httpRequestBytes))>0){
			httpRequest=new String(httpRequestBytes,0,length);
//			GET /girl HTTP/1.1\r\nHost: localhost:8080\r\nConnection: keep-alive\r\nPragma: no-cache\r\nCache-Control: no-cache\r\nUpgrade-Insecure-Requests: 1\r\nUser-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36\r\nAccept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\r\nAccept-Encoding: gzip, deflate, br\r\nAccept-Language: zh-CN,zh;q=0.9\r\nCookie: _ga=GA1.1.290496923.1504656889\r\n\r\n
			
//			GET /girl HTTP/1.1
//			Host: localhost:8080
//			Connection: keep-alive
//			Pragma: no-cache
//			Cache-Control: no-cache
//			Upgrade-Insecure-Requests: 1
//			User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36
//			Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
//			Accept-Encoding: gzip, deflate, br
//			Accept-Language: zh-CN,zh;q=0.9
//			Cookie: _ga=GA1.1.290496923.1504656889

//			\r 回车(CR) ，将当前位置移到本行开头
//			\n 换行(LF) ，将当前位置移到下一行开头
			
//			\作为转义字符开关，与不同字母匹配的时候起到不同作用。
//			写成\s，表示\与s相匹配，在第一次引用该段指令时就会生效，起到\s的作用。也就是表示空白符号。
//			而写成\\s的时候，\会与第二个\相匹配，形成\\。其含义就是单独的一个\。这种情况，有可能是为了输出\s，而不令其转义。
//			更常见的原因是该段字符串会被处理两次，第一次的时候将\\s处理为\s，第二次处理的时候，才执行\s的作用，即空白符号。
		}
		String httpHead=httpRequest.split("\n")[0];//GET /girl HTTP/1.1
		String[] split = httpHead.split("\\s");
		url=httpHead.split("\\s")[1];
		method=httpHead.split("\\s")[0];
		System.out.println(this);
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
}
