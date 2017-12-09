package application.controller;

import application.Login;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


/**
 * @author gresehyseni
 * 
 *         Final Project - 12/03/2017
 * 
 *         Connects with RootLayoutView and handles menu actions. Initializes
 *         Login class to access showView methods.
 */
public class RootLayoutController {

	@FXML
	private BorderPane bp;

	@FXML
	private MenuItem logoutMenuItem;
	@FXML
	private MenuItem exitMenuItem;
	@FXML
	private MenuItem profileMenuItem;
	@FXML
	private MenuItem ticketsMenuItem;
	@FXML
	private MenuItem usersMenuItem;

	/**
	 * Called by Login to pass parameters needed to be send to views. Handles menu
	 * items click events.
	 * 
	 * @param login
	 * @param user
	 * @param sessionID
	 */
	public void initialize(final Login login, User user, String sessionID) {

		login.showProfile(user);

		System.out.println("Logged in. User:" + user.getUsername());

		logoutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				login.logout();
			}
		});
		exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				login.logout();
				System.exit(0);
			}
		});

		profileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				login.showProfile(user);
			}
		});
		ticketsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				login.showTicketView(user);
			}
		});

		// Modify view/events based on user role
		if (user.getRole().equals("admin")) {
			usersMenuItem.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					login.showUserView(user);
				}
			});
		}

	}

	/**
	 * About menu is shown in a dialog box
	 * 
	 * @param actionEvent
	 */
	public void showAbout(ActionEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Trouble Ticket System - Grese Hyseni");
		alert.setContentText(
				"You can login as admin or employee, and you'll be able to manage users and all tickets if loggen id as admin, or only your tickets if logged in as employee.");
		alert.show();
	}

}
