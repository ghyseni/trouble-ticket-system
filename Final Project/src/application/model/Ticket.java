package application.model;

import javafx.beans.property.*;
/**
 * @author gresehyseni
 * 
 *         Final Project - 12/06/2017
 * 
 *         Provides a class that will be used to interact with Ticket data records, and
 *         use the results to populate fxml table
 */
public class Ticket {
	private IntegerProperty ticketId;
	private StringProperty name;
	private StringProperty description;
	private StringProperty department;
	private StringProperty issuer;
	private StringProperty status;
	private StringProperty createdAt;
	private StringProperty updatedAt;
	private StringProperty username;
	private IntegerProperty userId;

	/**
	 * Constructor. SimpleProperty used to fill table after
	 */
	public Ticket() {
		this.ticketId = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.department = new SimpleStringProperty();
		this.issuer = new SimpleStringProperty();		
		this.status = new SimpleStringProperty();
		this.createdAt = new SimpleStringProperty();
		this.updatedAt = new SimpleStringProperty();
		this.userId = new SimpleIntegerProperty();
		this.username = new SimpleStringProperty();
	}
	
	// Getters and Setters

	// ticketId
	public int getTicketId() {
		return ticketId.get();
	}

	public void setTicketId(int ticketId) {
		this.ticketId.set(ticketId);
	}

	public IntegerProperty ticketIdProperty() {
		return ticketId;
	}

	// name
	public String getName() {
		return name.get();
	}

	public void setName(String ticketname) {
		this.name.set(ticketname);
	}

	public StringProperty nameProperty() {
		return name;
	}

	// description
	public String getDescription() {
		return description.get();
	}

	public void setDescription(String firstName) {
		this.description.set(firstName);
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	// department
	public String getDepartment() {
		return department.get();
	}

	public void setDepartment(String departmentId) {
		this.department.set(departmentId);
	}

	public StringProperty departmentProperty() {
		return department;
	}

	// userid
	public int getUserId() {
		return userId.get();
	}

	public void setUserId(int userId) {
		this.userId.set(userId);
	}

	public IntegerProperty userIdProperty() {
		return userId;
	}

	// issuerid
	public String getIssuer() {
		return issuer.get();
	}

	public void setIssuer(String issuer) {
		this.issuer.set(issuer);
	}

	public StringProperty issuerProperty() {
		return issuer;
	}

	// createdAt
	public String getCreatedAt() {
		return createdAt.get();
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt.set(createdAt);
	}

	public StringProperty createdAtProperty() {
		return createdAt;
	}
	// updatedAt
	public String getUpdatedAt() {
		return updatedAt.get();
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt.set(updatedAt);
	}

	public StringProperty updatedAtProperty() {
		return updatedAt;
	}
	
	// status
	public String getStatus() {
		return status.get();
	}

	public void setStatus(String status) {
		this.status.set(status);
	}

	public StringProperty statusProperty() {
		return status;
	}
	
	// username
	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public StringProperty usernameProperty() {
		return username;
	}
}
