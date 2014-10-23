package edu.cmu.database;

import java.sql.*;

public class DbOperations {

	private static String sqlQuery;
	private Connection connection = null;
	private Statement statement = null;
	// JDBC driver name and database URL
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/sakila";
	//	//  Database credentials
	static final String USER = "root";
	static final String PASS = "P@$$w0r|)";

	public DbOperations() throws ClassNotFoundException, SQLException {
		super();
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());

		System.out.println("Connecting to database...");
		connection = DriverManager.getConnection(DB_URL,USER,PASS);

		System.out.println("Creating statement...");
		statement = connection.createStatement();
		// TODO Auto-generated constructor stub
	}

	public ResultSet callDatabaseQuery(String sqlQuery) throws SQLException {
		String sql;
		sql = sqlQuery;
		ResultSet rs = this.statement.executeQuery(sql);
		return rs;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DbOperations blah = new DbOperations();
		ResultSet rs = blah.callDatabaseQuery("SELECT * FROM sakila.actor");
		while(rs.next()){
			//Retrieve by column name
			String author = rs.getString("first_name");
			String coauthor = rs.getString("last_name");

			//Display values
			System.out.print("FirstName: " + author);
			System.out.print("=====");
			System.out.println("LastName: " + coauthor);
		}
	}
}
