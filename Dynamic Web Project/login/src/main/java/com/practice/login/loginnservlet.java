package com.practice.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginnservlet
 */
@WebServlet("/login")

///To check the user entering the login details are correct or wrong.
public class loginnservlet extends HttpServlet {
	String empname,empDes,pwd;
	int empid,empsalary;
    String validate="select * from `employee` where `email`=?";
	
	static private Connection con;
	
	private static PreparedStatement pstmt;
	
	private static ResultSet res;
	
	private static RequestDispatcher rd;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root@123");
		
	
			pstmt=con.prepareStatement(validate);
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String email = req.getParameter("email");
		 String password = req.getParameter("password");
		 
		 
		 try {
			 
			 pstmt.setString(1, email);
			
			 res = pstmt.executeQuery();
			 //if there is data
			 if(res.next())
			 {
				if(password .equals( res.getString("password")))
				{
					
					
					empid=res.getInt("empid");
					empname=res.getString("empname");
					empDes=res.getString("empDes");
					empsalary=res.getInt("empsalary");
					pwd=res.getString("password");
					
					HttpSession session=req.getSession();
					
					session.setAttribute("empid", empid);
					session.setAttribute("empname", empname);
					session.setAttribute("empDes", empDes);
					session.setAttribute("empsalary", empsalary);
					session.setAttribute("pwd", pwd);
					session.setAttribute("email", email);
					
					
					
					rd=req.getRequestDispatcher("DisplayServlet");
					rd.forward(req, resp);
					
				}
				else
				{
					
					resp.sendRedirect("Invalid.html");
				}
			 }
			 else
			 {
				 resp.sendRedirect("Login.html");
			 }
			
		} catch (SQLException e) {
			
		    e.printStackTrace();
		}
	}
}
