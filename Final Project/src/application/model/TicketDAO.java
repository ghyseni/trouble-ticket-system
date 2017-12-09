package application.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author gresehyseni
 * 
 *         Final Project - 12/06/2017
 * 
 *         Provides the interaction with the database directly.
 */
public class TicketDAO {
	
	/**
	 * Call to create table g_hyse_tickets
	 */
	public static void createTable() {

		String createTableStmt = "CREATE TABLE g_hyse_tickets (" + 
				" ticket_id int(10) UNSIGNED NOT NULL AUTO_INCREMENT," + 
				" name varchar(30) NOT NULL," + 
				" description varchar(200) NOT NULL," + 
				" department varchar(30) NOT NULL," + 
				" issuer varchar(30) NOT NULL," + 
				" status varchar(10) NOT NULL," + 
				" created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP," + 
				" updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," + 
				" user_id int(11) NOT NULL, PRIMARY KEY ( ticket_id ))";

		// Execute CREATE statement
		try {
			DBUtil.executeUpdate(createTableStmt);
		} catch (SQLException e) {
			System.out.println("While creating table g_hyse_tickets, error occured." + e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	/**
	 * SELECT Ticket by ticket id
	 * 
	 * @param ticketId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Ticket searchTicket(String ticketId) throws SQLException, ClassNotFoundException {
		// Declare a SELECT statement

		String selectStmt = "SELECT * FROM g_hyse_tickets INNER JOIN g_hyse_users ON g_hyse_tickets.user_id=g_hyse_users.user_id WHERE ticket_id="
				+ ticketId;
		System.out.println(selectStmt);

		// Execute SELECT statement
		try {
			ResultSet rsTicket = DBUtil.executeQuery(selectStmt);
			Ticket ticket = getTicketFromResultSet(rsTicket);
			return ticket;
		} catch (SQLException e) {
			System.out.println("While searching an ticket with " + ticketId + " id, an error occurred: " + e);
			throw e;
		}
	}

	/**
	 * Set Ticket Object's attributes from DB ResultSet.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static Ticket getTicketFromResultSet(ResultSet rs) throws SQLException {
		Ticket ticket = null;
		if (rs.next()) {
			ticket = new Ticket();
			ticket.setTicketId(rs.getInt("ticket_id"));
			ticket.setName(rs.getString("name"));
			ticket.setDescription(rs.getString("description"));
			ticket.setDepartment(rs.getString("department"));
			ticket.setIssuer(rs.getString("issuer"));
			ticket.setUserId(rs.getInt("user_id"));
			ticket.setUsername(rs.getString("username"));
			ticket.setStatus(rs.getString("status"));
			ticket.setCreatedAt(rs.getTimestamp("created_at").toString());
			ticket.setUpdatedAt(rs.getTimestamp("updated_at").toString());
		}
		return ticket;
	}

	/**
	 * SELECT Tickets by keyword, status
	 * 
	 * @param keyword
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ObservableList<Ticket> searchTickets(String keyword, String status)
			throws SQLException, ClassNotFoundException {

		if (status == null || status.isEmpty()) {
			status = "%";
		}
		if (keyword.isEmpty()) {
			keyword = "%";
		} else {
			keyword = "%" + keyword + "%";
		}

		String selectStmt = "SELECT * FROM g_hyse_tickets INNER JOIN g_hyse_users ON g_hyse_tickets.user_id=g_hyse_users.user_id WHERE (name LIKE '"
				+ keyword + "' OR description LIKE '" + keyword + "') AND status LIKE '" + status + "'";

		// Execute SELECT statement
		try {
			ResultSet rsTickets = DBUtil.executeQuery(selectStmt);
			ObservableList<Ticket> ticketList = getTicketList(rsTickets);
			return ticketList;
		} catch (SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
			throw e;
		}
	}

	/**
	 * SELECT Tickets by keyword, status, userId
	 * 
	 * @param keyword
	 * @param status
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ObservableList<Ticket> searchTickets(String keyword, String status, int userId)
			throws SQLException, ClassNotFoundException {

		if (status == null || status.isEmpty()) {
			status = "%";
		}
		if (keyword.isEmpty()) {
			keyword = "%";
		} else {
			keyword = "%" + keyword + "%";
		}

		String selectStmt = "SELECT * FROM g_hyse_tickets INNER JOIN g_hyse_users ON g_hyse_tickets.user_id=g_hyse_users.user_id WHERE (name LIKE '" + keyword + "' OR description LIKE '" + keyword
				+ "') AND status LIKE '" + status + "' AND g_hyse_tickets.user_id=" + userId;

		// Execute SELECT statement
		try {
			ResultSet rsTickets = DBUtil.executeQuery(selectStmt);
			ObservableList<Ticket> ticketList = getTicketList(rsTickets);
			return ticketList;
		} catch (SQLException e) {
			System.out.println("SQL select operation has failed: " + e);
			throw e;
		}
	}

	/**
	 * Select * from g_hyse_tickets. Set Ticket Object's attributes from DB ResultSet.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static ObservableList<Ticket> getTicketList(ResultSet rs) throws SQLException, ClassNotFoundException {

		ObservableList<Ticket> userList = FXCollections.observableArrayList();

		while (rs.next()) {
			Ticket ticket = new Ticket();
			ticket.setTicketId(rs.getInt("ticket_id"));
			ticket.setName(rs.getString("name"));
			ticket.setDescription(rs.getString("description"));
			ticket.setDepartment(rs.getString("department"));
			ticket.setUserId(rs.getInt("user_id"));
			ticket.setUsername(rs.getString("username"));
			ticket.setIssuer(rs.getString("issuer"));
			ticket.setStatus(rs.getString("status"));
			ticket.setCreatedAt(rs.getTimestamp("created_at").toString());
			ticket.setUpdatedAt(rs.getTimestamp("updated_at").toString());

			userList.add(ticket);
		}
		return userList;
	}

	/**
	 * Update ticket
	 * 
	 * @param ticketId
	 * @param ticketName
	 * @param ticketDescription
	 * @param ticketDepartment
	 * @param issuer
	 * @param userId
	 * @param status
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void updateTicket(String ticketId, String ticketName, String ticketDescription,
			String ticketDepartment, String issuer, int userId, String status)
			throws SQLException, ClassNotFoundException {

		String updateStmt = "UPDATE g_hyse_tickets SET name = '" + ticketName + "', description = '" + ticketDescription
				+ "', department = '" + ticketDepartment + "', issuer = '" + issuer + "', user_id = " + userId
				+ ", status='" + status + "' WHERE ticket_id = " + ticketId;

		// Execute UPDATE operation
		try {
			DBUtil.executeUpdate(updateStmt);
		} catch (SQLException e) {
			System.out.print("Error while UPDATE ticket name: " + e);
			throw e;
		}
	}

	/**
	 * Delete ticket
	 * 
	 * @param ticketId
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void deleteTicketWithId(String ticketId) throws SQLException, ClassNotFoundException {

		String updateStmt = "DELETE FROM g_hyse_tickets WHERE ticket_id =" + ticketId;

		// Execute UPDATE operation
		try {
			DBUtil.executeUpdate(updateStmt);
		} catch (SQLException e) {
			System.out.print("Error while DELETE ticket: " + e);
			throw e;
		}
	}

	/**
	 * Insert ticket
	 * 
	 * @param name
	 * @param description
	 * @param department
	 * @param issuer
	 * @param userId
	 * @param status
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertTicket(String name, String description, String department, String issuer, int userId,
			String status) throws SQLException, ClassNotFoundException {

		String updateStmt = "INSERT INTO g_hyse_tickets " + "(name, description, department, issuer, user_id, status) "
				+ "VALUES " + "('" + name + "','" + description + "','" + department + "','" + issuer + "'," + userId
				+ ",'" + status + "')";
		System.out.println(updateStmt);
		// Execute DELETE operation
		try {
			DBUtil.executeUpdate(updateStmt);
		} catch (SQLException e) {
			System.out.print("Error while insert Ticket: " + e);
			throw e;
		}
	}

}
