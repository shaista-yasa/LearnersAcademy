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

import com.java.details.TeacherDetails;
import com.java.util.Util;

import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ListTeachers
 */
@WebServlet(name = "listteachers", urlPatterns = { "/listteachers" })
public class ListTeachers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTeachers() {
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
			
			List<TeacherDetails> list = session.createQuery("from TeacherDetails").list();
			
			out.println("<h2>Teacher List");
			
			out.println("<style> table,td,th{"
					+ "border:2px solid red; "
					+ "padding:2px;); "
					+ "</style>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>S.No</th>");
			out.println("<th>Teacher Name</th>");
			out.println("<th>Teacher Subject</th>");
			out.println("<th>Teacher Class</th>");
			out.println("<tr>");
			
			for(TeacherDetails teachdet : list) {
				out.println("<tr>");
				out.println("<td>"+ teachdet.getId() +"</td>");
				out.println("<td>"+ teachdet.getName() +"</td>");
				out.println("<td>"+ teachdet.getSubject() +"</td>");
				out.println("<td>"+ teachdet.getTeacherClass() +"</td>");
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
