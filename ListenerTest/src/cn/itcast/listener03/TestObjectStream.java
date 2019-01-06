package cn.itcast.listener03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestObjectStream {
	public static void main(String[] args) throws Exception {
		test01();
		test02();
	}

	private static void test02() throws Exception {
		//从磁盘读取
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("I:\\1.txt"));
		Stu s1 = (Stu)ois.readObject();
		Stu s2 = (Stu)ois.readObject();
		Stu s3 = (Stu)ois.readObject();
		ois.close();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		
	}

	private static void test01() throws Exception {
		//写入磁盘
		Stu s1=new Stu("rose");
		Stu s2=new Stu("jack");
		Stu s3=new Stu("lucy");
		
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("I:\\1.txt"));
		oos.writeObject(s1);
		oos.writeObject(s2);
		oos.writeObject(s3);
		oos.flush();
		oos.close();
		System.out.println("write ok");
		
	}

}
