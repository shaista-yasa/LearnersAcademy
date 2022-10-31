package com.java.operations;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java.util.Util;

import jakarta.servlet.annotation.WebServlet;


@WebServlet(name = "init", urlPatterns = { "/init" })
public class InitializeConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.jsp").include(request, response);
		
		try {
			SessionFactory factory = Util.buildConnection();
			Session session = factory.openSession();
			
			if(session != null) {
				out.println("<div align='center'><h2 style='color:green'>Successfully Connected</h2></div>");
			}
			session.close();
		} catch (Exception e) {
				out.println("<div align='center'><h2 style='color:red'>Connection Failed</h2></div>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
