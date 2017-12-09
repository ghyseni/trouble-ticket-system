package application.util;

import java.sql.*;

/**
 * @author gresehyseni
 * 
 *         Final Project - 12/01/2017
 * 
 *         Provides helpful methods to perform db operations. This avoids code
 *         repetition.
 */
public class DBUtil {
	// JDBC Driver
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	// Connection fields
	// private static Connection conn = null;
	// static final String DATABASE_NAME = "test";
	// static final String DB_URL = "jdbc:mysql://localhost/" + DATABASE_NAME;
	// static final String USER = "root";
	// static final String PASS = "";

	private static Connection conn = null;
	static final String DB_URL = "jdbc:mysql://www.papademas.net:3306/tickets?autoReconnect=true&useSSL=false";
	static final String USER = "fp411";
	static final String PASS = "411";

	/**
	 * Connect to DB
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void connect() throws SQLException, ClassNotFoundException {
		// JDBC Driver
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Problem with JDBC Driver?");
			e.printStackTrace();
			throw e;
		}

		// Establish connection
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Connection Failed!" + e);
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Close Connection
	 * 
	 * @throws SQLException
	 */
	public static void disconnect() throws SQLException {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * DB Execute Query Operation
	 * 
	 * @param queryStmt
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet executeQuery(String stm) throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect();
			System.out.println("Statement: " + stm);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(stm);
		} catch (SQLException e) {
			System.out.println("Problem while executing query. " + e);
			throw e;
		}
		return rs;
	}

	/**
	 * DB Execute Update (For Update/Insert/Delete) Operation
	 * 
	 * @param sqlStmt
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void executeUpdate(String stm) throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate(stm);
			System.out.println("Statement: " + stm);
		} catch (SQLException e) {
			System.out.println("Problem while executing query. " + e);
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			disconnect();
		}
	}
}