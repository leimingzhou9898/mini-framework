package mytomcat;


public class FindGirlServlet extends MyServlet {

	@Override
	public void doGet(MyRequest myRequst, MyResponse myResponse) {
		try {
			myResponse.write("get girl...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(MyRequest myRequst, MyResponse myResponse) {
		try {
			myResponse.write("get girl...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	

}
