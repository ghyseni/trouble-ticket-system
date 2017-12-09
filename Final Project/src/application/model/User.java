	package application.model;

import javafx.beans.property.*;

/**
 * @author gresehyseni
 * 
 *         Final Project - 12/06/2017
 * 
 *         Provides a class that will be used to interact with User data records, and
 *         use the results to populate fxml table
 */
public class User {
	private IntegerProperty userId;
	private StringProperty username;
	private StringProperty password;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty role;

	/**
	 * Constructor. SimpleProperty used to fill table after
	 */
	public User() {
		this.userId = new SimpleIntegerProperty();
		this.username = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
		this.firstName = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.role = new SimpleStringProperty();
	}
	
	// Getters and Setters

	// userId
	public int getUserId() {
		return userId.get();
	}

	public void setUserId(int userId) {
		this.userId.set(userId);
	}

	public IntegerProperty userIdProperty() {
		return userId;
	}

	//username
	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public StringProperty usernameProperty() {
		return username;
	}

	// password
	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public StringProperty passwordProperty() {
		return password;
	}

	// firstName
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	// lastName
	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	// role
	public String getRole() {
		return role.get();
	}

	public void setRole(String role) {
		this.role.set(role);
	}

	public StringProperty roleProperty() {
		return role;
	}

}
