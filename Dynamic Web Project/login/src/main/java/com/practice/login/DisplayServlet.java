package com.practice.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DisplayServlet
 */
@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
	PrintWriter pw;
       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	pw=resp.getWriter();
    	
    	HttpSession session = req.getSession();
    	
    	int empid=(int)session.getAttribute("empid");
    	String empname=(String) session.getAttribute("empname");
		String empDes=(String) session.getAttribute("empDes");
		int empsalary=(int) session.getAttribute("empsalary");
		String pwd=(String) session.getAttribute("pwd");
		String email=(String) session.getAttribute("email");
		
		
		pw.println(empid +" "+empname+" "+" "+empDes+" "+" "+empsalary+" "+" "+pwd+" "+" "+email);
    }

}
