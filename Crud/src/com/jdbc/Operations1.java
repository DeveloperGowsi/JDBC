package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operations1 {
	static String Url="jdbc:mysql://localhost:3306/sys";
	static String Username="root";
	static String Password="root@123";
	static Connection con;
    static Statement stmt;
    static  ResultSet result;
    public static void main(String args[])
    {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		    con=DriverManager.getConnection(Url,Username,Password);
		    
		    stmt=con.createStatement();
		    
		    result=stmt.executeQuery("Select * from Student_table");
		    
		    while(result.next())
		    {
		    	System.out.println(result.getString(1));
		    	System.out.println(result.getInt(2));
		    	System.out.println(result.getString(3));
		    	System.out.println(result.getString(4));
		    	System.out.println(result.getLong(5));
		    	
		    }
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
    	
    }
}
