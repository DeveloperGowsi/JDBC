package com.jdbc;

import java.util.ArrayList;

public class Mainclass {

	public static void main(String args[])
	{
		//User u=new User("Akhil","123","akhil@gmail.com","Mysuru","Software Developer");
		
		UserDAo udao=new UserDaoimpl();//interface reference to implementing class object.
		
		//int status=udao.insertUser(u);
		
		//System.out.println(status);
		
		ArrayList<User> users=(ArrayList<User>) udao.getAllUsers();
		for(User u: users)
		{
			System.out.println(u);
		}
	}
}
