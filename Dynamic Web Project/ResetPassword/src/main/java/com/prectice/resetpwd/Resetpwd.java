package com.prectice.resetpwd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Resetpwd extends HttpServlet {
	
	
    String password1="Update `employee` set `password`=?  where `email`=?";

    String fetch="select * from `employee` where `email`=?";
    
	String email,password,cpassword;
	
	static private Connection con;
	
	private static PreparedStatement pstmt;
	
	private static ResultSet res;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root@123");
		
	
			pstmt=con.prepareStatement(password1);
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
    
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String password=req.getParameter("password");
			String cpassword=req.getParameter("cpassword");
			
			
			if(password.equals(cpassword))
			{
				email=req.getParameter("email");
				pstmt.setString(1,password);
				pstmt.setString(2, email);
				
				int x=pstmt.executeUpdate();
				if(x==0)
				{
					resp.sendRedirect("failure.html");
				}
				else
				{
					pstmt=con.prepareStatement(fetch);
					
					pstmt.setString(1, email);
					
					 res = pstmt.executeQuery();
					 
					 while(res.next())
					 {
						 String name=res.getString("empname");
						 String Des=res.getString("empDes");
					     String  salary=res.getString("empsalary");
						 
						 
						 Cookie c1=new Cookie("name", name);
						 
						 Cookie c2=new Cookie("Des", Des);
						 
						 Cookie c3=new Cookie("salary", salary);
						 
						 c1.setMaxAge(60);
						 
						 c2.setMaxAge(90);
						 
						 c3.setMaxAge(120);
						 
						 resp.addCookie(c1);
						 
						 resp.addCookie(c2);
						 
						 resp.addCookie(c3);
						
					 }
					resp.sendRedirect("Success.html");
				}
				
			}
			else
			{
				resp.sendRedirect("Passwordmismatch.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}

}
