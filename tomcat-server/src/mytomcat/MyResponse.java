package mytomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyResponse {
	private OutputStream outputStream;
	public MyResponse(OutputStream outputStream) throws Exception {
		this.outputStream=outputStream;
	}
	public void write(String content) throws IOException{
		StringBuffer httpResponse=new StringBuffer();
		httpResponse.append("HTTP/1.1 200 OK\n")
			.append("Content-Type: text/html\n")
			.append("\r\n")
			.append("<html><body>")
			.append(content)
			.append("</body></html>");
		outputStream.write(httpResponse.toString().getBytes());
		outputStream.close();
	}
}
