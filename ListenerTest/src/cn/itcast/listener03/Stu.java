package cn.itcast.listener03;

import java.io.Serializable;

public class Stu implements Serializable{
	public Stu(){
		
	}
	public Stu(String username){
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
		return "Stu [username=" + username + "]";
	}
	
	
}
