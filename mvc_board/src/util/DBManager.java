package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {

	public static DBManager instance = null;
	
	private DBManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static DBManager getInsance() {
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "board_mvc";
		String pwd = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pwd);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void freeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void freeConnection(Connection conn, Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
