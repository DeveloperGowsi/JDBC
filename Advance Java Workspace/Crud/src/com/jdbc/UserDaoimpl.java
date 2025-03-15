package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoimpl implements UserDAo{
	
	static Connection con;
	static PreparedStatement pstmt;
	static Statement stmt;
	static ResultSet result;

	static String  Url="jdbc:mysql://localhost:3306/sys";
	static String Username="root";
	static String Password="root@123";
	private final static String sqlInsert="insert into user(`Username`,`Pswd`,`email`,`address`,`role`)values(?,?,?,?,?)";
	
	private final static String sqlSelect="Select * from user";
	
    ArrayList<User> userlist=new ArrayList<User>();
	
	static {
	
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(Url,Username,Password);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public int insertUser(User user) {
		int x=-1;
		try {
			
			pstmt=con.prepareStatement(sqlInsert);
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword1());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getRole());
			
			x=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		return x;
	
	}

	@Override
	public  List<User> getAllUsers() {
		try {
			stmt = con.createStatement();
			
			result=stmt.executeQuery(sqlSelect);//For every row we are creating an object,we are storing that objects as a list of objects.
			
			userlist=(ArrayList<User>) extractUserResultSet(result);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return userlist;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUserById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUserById(int id, String address) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	List<User> extractUserResultSet(ResultSet resultset)
	{

	  try {
	    while(result.next())
	    {
		   userlist.add(new User(result.getInt(1),
				   result.getString(2),
				   result.getString(3),
				   result.getString(4),
				   result.getString(5),
				   result.getString(6)));
	    }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return userlist;

}
}
