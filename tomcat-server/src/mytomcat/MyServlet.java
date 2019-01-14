package mytomcat;

public abstract class MyServlet {
	public abstract void doGet(MyRequest myRequst,MyResponse myResponse);
	public abstract void doPost(MyRequest myRequst,MyResponse myResponse);
	public void service(MyRequest myRequst,MyResponse myResponse){
		if(myRequst.getMethod().equalsIgnoreCase("POST")){
			doPost(myRequst, myResponse);
		}else if(myRequst.getMethod().equalsIgnoreCase("GET")){
			doGet(myRequst, myResponse);
		}
	}
}
