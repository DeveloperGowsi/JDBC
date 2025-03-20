package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insertemployee")
public class insertEmployee extends HttpServlet {
	
	String insert="insert into `employee`(`empid`,`empname`,`empDes`,`empsalary`) values (?,?,?,?)";
	
	static private Connection con;
	
	private static PreparedStatement pstmt;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root@123");
		
	
			pstmt=con.prepareStatement(insert);
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
       
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	int empid=Integer.parseInt(req.getParameter("employeeid"));
    	
    	String empname=req.getParameter("employeename");
    	
    	String empDes=req.getParameter("employeeDes");
    	
    	int empsalary=Integer.parseInt(req.getParameter("employeesalary"));
    	
    	PrintWriter pw=resp.getWriter();
    	
    //	pw.println(empid+" "+empname+" "+empDes+" "+"  "+empsalary);
    	try {
			
			
			pstmt.setInt(1, empid);
			pstmt.setString(2, empname);
			pstmt.setString(3, empDes);
			pstmt.setInt(4, empsalary);
			
			int status=pstmt.executeUpdate();
			
			
			pw.println(status);
			if(status==0)
			{
				pw.println(empid+" "+empname+" "+empDes+" "+"  "+empsalary);
				resp.sendRedirect("failure.html");
			}
			else 
			{
				resp.sendRedirect("success.html");
				
			}
			 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    }

}
