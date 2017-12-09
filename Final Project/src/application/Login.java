package application;

import application.controller.LoginController;
import application.controller.ProfileController;
import application.controller.RootLayoutController;
import application.controller.TicketController;
import application.controller.TasksController;
import application.controller.UserController;
import application.model.TaskDAO;
import application.model.Ticket;
import application.model.TicketDAO;
import application.model.User;
import application.model.UserDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * @author gresehyseni
 * 
 *         Final Project - 12/01/2017
 * 
 *         Start application, providing methods to show different views.
 */
public class Login extends Application {
	private Scene scene;
	private Stage stage;
	private BorderPane rootLayout;
	

	public static void main(String[] args) {	
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		
//		TicketDAO.createTable();
//		UserDAO.createTable();
//		UserDAO.InsertUsers();
//		TaskDAO.createTable();
		
		scene = new Scene(new StackPane());
		this.stage = stage;
		showLoginView(); 

		stage.setScene(scene);
		stage.setTitle("Trouble Ticket System - Login");
		stage.show();
	}

	/**
	 * Method will be called after user authenticated. Shows main window.
	 * 
	 * @param user
	 * @param sessionID
	 */
	public void authenticate(User user, String sessionID) {
		showMainView(user, sessionID);
	}

	/**
	 * Method will be called after user is logged out, opens login window.
	 */
	public void logout() {
		showLoginView();
	}

	/**
	 * Open Login view
	 */
	public void showLoginView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Login.class.getResource("view/Login.fxml"));
			scene.setRoot((Parent) loader.load());
			LoginController controller = loader.<LoginController>getController();
			controller.init(this);
			stage.sizeToScene();
		} catch (IOException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Open root layout, where other views are going to be set in.
	 * 
	 * @param user
	 * @param sessionID
	 */
	public void showMainView(User user, String sessionID) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Login.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			RootLayoutController controller = loader.<RootLayoutController>getController();
			controller.initialize(this, user, sessionID);

			scene.setRoot(rootLayout);
			stage.sizeToScene();
			stage.centerOnScreen();
			stage.setTitle("Trouble Ticket System - Profile");

		} catch (IOException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	/**
	 * Shows ticket view inside root layout.
	 * 
	 * @param user
	 */
	public void showTicketView(User user) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Login.class.getResource("view/TicketView.fxml"));
			AnchorPane ticketView = (AnchorPane) loader.load();
			rootLayout.setCenter(ticketView);
			TicketController controller = loader.<TicketController>getController();
			controller.init(this, user);

			stage.setTitle("Trouble Ticket System - Tickets");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Shows profile view inside root layout.
	 * 
	 * @param user
	 */
	public void showProfile(User user) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Login.class.getResource("view/ProfileView.fxml"));
			AnchorPane profileView = (AnchorPane) loader.load();
			rootLayout.setCenter(profileView);
			ProfileController controller = loader.<ProfileController>getController();
			controller.init(this, user);

			stage.setTitle("Trouble Ticket System - Profile");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Shows user view inside root layout.
	 * 
	 * @param user
	 */
	public void showUserView(User user) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Login.class.getResource("view/UserView.fxml"));
			AnchorPane userView = (AnchorPane) loader.load();
			rootLayout.setCenter(userView);
			UserController controller = loader.<UserController>getController();
			controller.init(this, user);

			stage.setTitle("Trouble Ticket System - Users");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Shows tasks view inside root layout.
	 * 
	 * @param user
	 * @param ticket
	 */
	public void showOpenTicketView(User user, Ticket ticket) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Login.class.getResource("view/TasksView.fxml"));
			AnchorPane ticketOpenView = (AnchorPane) loader.load();
			rootLayout.setCenter(ticketOpenView);
			TasksController controller = loader.<TasksController>getController();
			controller.init(this, user, ticket);

			stage.setTitle("Trouble Ticket System - Tasks");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}