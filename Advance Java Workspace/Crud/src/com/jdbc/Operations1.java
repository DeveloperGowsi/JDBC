package com.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Operations1 {
	private static Connection con;
	private static Statement stmt;
	private static ResultSet resultSet;
	private static ResultSetMetaData rsmd;
	private static DatabaseMetaData dbmd;
	private static ParameterMetaData pmd;
	private static PreparedStatement pstmt;
	static String Url="jdbc:mysql://localhost:3306/sys";
	static String Username="root";
	static String Password="root@123";
	
	static String sql="select * from student_table where `student id`=?";
	public static void main(String args[])
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		    con=DriverManager.getConnection(Url,Username,Password);
			
			stmt=con.createStatement();
			
			resultSet=stmt.executeQuery("select * from student_table");
			
			dbmd=con.getMetaData();
			
			System.out.println("Database Name: "+dbmd.getDatabaseProductName());
			
			System.out.println("Database Driver: "+dbmd.getDriverName());
			
			System.out.println("Database MaxConnection: "+dbmd.getMaxConnections());
			
			rsmd =resultSet.getMetaData();
			
			int count=rsmd.getColumnCount();
			
			System.out.println("No of Columns:"+count);
			
			for(int i=1;i<=count;i++)
			{
				System.out.print(rsmd.getColumnName(i)+" ");
				System.out.print(rsmd.getColumnTypeName(i)+" ");
				System.out.println(rsmd.getColumnType(i));
				
			}
			
			
		
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
}
