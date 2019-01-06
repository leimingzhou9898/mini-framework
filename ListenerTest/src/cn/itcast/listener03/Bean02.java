package cn.itcast.listener03;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class Bean02 implements HttpSessionActivationListener, Serializable {
	
	public Bean02(){
		
	}
	
	public Bean02(String username){
		this.username=username;
	}

	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	@Override
	public String toString() {
		return "Bean02 [username=" + username + "]";
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
