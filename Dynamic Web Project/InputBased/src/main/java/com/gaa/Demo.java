package com.gaa;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Demo extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     PrintWriter pw=resp.getWriter();
      
        String name=req.getParameter("username");
       
        pw.println("Welcome"+" " +name);
       
       //resp.sendRedirect("testServerlet");
    	
    	RequestDispatcher rd=req.getRequestDispatcher("testServerlet");
    	
    	rd.include(req, resp);
       
    }
}
