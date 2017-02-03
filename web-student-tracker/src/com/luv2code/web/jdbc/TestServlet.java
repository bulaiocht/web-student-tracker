package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker") //same Resource name as in context.xml
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Set up PrintWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		//Get a connection to the database
		
		Statement statement = null;
		ResultSet resultSet = null;
		
	
		try(Connection connection = dataSource.getConnection()) {
			
			//Create a SQL statement
			String sql = "SELECT * FROM student";
			statement = connection.createStatement();
			
			//Execute SQL queue
			resultSet = statement.executeQuery(sql);
			
			
			//Process ResultSet
			while(resultSet.next()){
				
				out.append(resultSet.getString("first_name") + " ");
				out.append(resultSet.getString("last_name") + ": ");
				out.append(resultSet.getString("email"));
				out.toString();
				out.print("\n");
				out.flush();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
