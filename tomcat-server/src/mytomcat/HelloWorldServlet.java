package mytomcat;


public class HelloWorldServlet extends MyServlet {

	@Override
	public void doGet(MyRequest myRequst, MyResponse myResponse) {
		try {
			myResponse.write("get world...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(MyRequest myRequst, MyResponse myResponse) {
		try {
			myResponse.write("get world...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
