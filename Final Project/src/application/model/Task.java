package application.model;

import javafx.beans.property.*;

/**
 * @author gresehyseni
 * 
 *         Final Project - 12/06/2017
 * 
 *         Provides a class that will be used to interact with Task data records, and
 *         use the results to populate fxml table
 */
public class Task {
	private IntegerProperty taskId;
	private StringProperty name;
	private StringProperty description;
	private StringProperty assignedTo;
	private StringProperty status;
	private StringProperty createdAt;
	private StringProperty updatedAt;
	private IntegerProperty ticketId;

	/**
	 * Constructor. SimpleProperty used to fill table after
	 */
	public Task() {
		this.taskId = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.assignedTo = new SimpleStringProperty();
		this.status = new SimpleStringProperty();
		this.ticketId = new SimpleIntegerProperty();
		this.createdAt = new SimpleStringProperty();
		this.updatedAt = new SimpleStringProperty();
	}

	// Getters and Setters

	// id
	public int getTaskId() {
		return taskId.get();
	}

	public void setTaskId(int taskId) {
		this.taskId.set(taskId);
	}

	public IntegerProperty taskIdProperty() {
		return taskId;
	}

	// name
	public String getName() {
		return name.get();
	}

	public void setName(String taskname) {
		this.name.set(taskname);
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

	// assignedTo
	public String getAssignedTo() {
		return assignedTo.get();
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo.set(assignedTo);
	}

	public StringProperty assignedToProperty() {
		return assignedTo;
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

	// taskId
	public int getTicketId() {
		return ticketId.get();
	}

	public void setTicketId(int ticketId) {
		this.ticketId.set(ticketId);
	}

	public IntegerProperty ticketIdProperty() {
		return ticketId;
	}

}
