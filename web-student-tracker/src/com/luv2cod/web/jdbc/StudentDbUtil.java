package com.luv2cod.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource){
		this.dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws SQLException {
		
		List<Student> students = new ArrayList<>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		//Get Connection
		try (Connection connection = dataSource.getConnection()) {
			
			//Create sql statement
			String sql = "SELECT * FROM student order by last_name";
			statement = connection.createStatement();
			
			//execute query
			resultSet = statement.executeQuery(sql);
			
			//process ResultSet
			while(resultSet.next()){
				
				//retrieve data from ResultSet
				
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				
				//Create new Student object
				Student student = new Student(id, firstName, lastName, email);
				
				//add Student to the List of students
				students.add(student);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			close(resultSet, statement);
		}
		
		return students;
	}

	private void close(ResultSet resultSet, Statement statement) {
		try {
			if (resultSet!=null) {
				resultSet.close();
			}
			
			if (statement!=null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
}
