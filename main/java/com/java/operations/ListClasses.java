package com.java.operations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java.details.ClassDetails;
import com.java.util.Util;

import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ListClasses
 */
@WebServlet(name = "listclasses", urlPatterns = { "/listclasses" })
public class ListClasses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListClasses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			SessionFactory factory = Util.buildConnection();
			
			Session session = factory.openSession();
			
			List<ClassDetails> list = session.createQuery("from ClassDetails").list();
			
			out.println("<h2>Class List");
			
			out.println("<style> table,td,th{"
					+ "border:2px solid red; "
					+ "padding:2px;); "
					+ "</style>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>S.No</th>");
			out.println("<th>Class Name</th>");
			out.println("<th>Section</th>");
			out.println("<th>Room No</th>");
			out.println("<tr>");
			
			for(ClassDetails classdet : list) {
				out.println("<tr>");
				out.println("<td>"+ classdet.getId() +"</td>");
				out.println("<td>"+ classdet.getClassName() +"</td>");
				out.println("<td>"+ classdet.getClassSection()+"</td>");
				out.println("<td>"+ classdet.getClassRoomNo() +"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<a href='admin-page.html'>Back To MainMenu</a>");
			session.close();
		} catch (Exception e) {
			out.println("<h2 style='color:red'>Operation Failed<h2>");
			out.println("<a href='admin-page.html'>Back To MainMenu</a>");
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
