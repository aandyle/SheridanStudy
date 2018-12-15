package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static Connection conn = null;
	
	public static Connection getConnection(){
		
		try {
			
			String url = "jdbc:mysql://prog32758-project.cexzblhcpqnu.us-east-2.rds.amazonaws.com:3306/project";
			
			String username = "admin";
			
			String password = "prog32758";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, username, password);
			
		} catch (ClassNotFoundException e) {
		
			e.getMessage();
		
		} catch (SQLException e) {
			
			e.getMessage();
		
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection conn){
		
		try {
			
			conn.close();
		
		} catch (SQLException e) {
		
			e.getMessage();
		
		}
		
	}
	
}
