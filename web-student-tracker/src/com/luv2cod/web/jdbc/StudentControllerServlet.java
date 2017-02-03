package com.luv2cod.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//create our StudentDbUtil object and pass it to connection pool/datasource
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//list the students in MVC fashion
			listStudents(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		
		//get students from database utility
		List<Student> students = studentDbUtil.getStudents();
		
		// add students to the request as an attribute
		request.setAttribute("STUDENT_LIST", students);
		
		//send list to JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
