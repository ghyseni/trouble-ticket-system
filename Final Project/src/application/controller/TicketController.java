package application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.Optional;

import application.Login;
import application.model.Ticket;
import application.model.TicketDAO;
import application.model.User;

/**
 * @author gresehyseni
 * 
 *         Final Project - 12/04/2017
 * 
 *         Connects with TicketView and Ticket Model, by interacting with both.
 */
public class TicketController {

	private User user;

	// AnchorPane
	@FXML
	private AnchorPane ap;

	// Inputs
	@FXML
	private TextField ticketIdText;
	@FXML
	private TextField ticketNameText;
	@FXML
	private TextArea ticketDescriptionTextArea;
	@FXML
	private ComboBox<String> departmentCombo;
	@FXML
	private TextField ticketIssuerText;
	@FXML
	private TextField ticketKeywordText;
	@FXML
	private ComboBox<String> searchStatusCombo;
	@FXML
	private ComboBox<String> statusCombo;

	// Buttons
	@FXML
	private Button searchTicketsBtn;
	@FXML
	private Button searchTicketBtn;
	@FXML
	private Button updateTicketBtn;
	@FXML
	private Button addTicketBtn;
	@FXML
	private Button deleteTicketBtn;
	@FXML
	private Button closeTicketBtn;
	@FXML
	private Button openTicketBtn;

	// Table
	@FXML
	private TableView<Ticket> ticketTable;
	@FXML
	private TableColumn<Ticket, Integer> ticketIdColumn;
	@FXML
	private TableColumn<Ticket, String> ticketNameColumn;
	@FXML
	private TableColumn<Ticket, String> ticketDescriptionColumn;
	@FXML
	private TableColumn<Ticket, String> ticketDepartmentColumn;
	@FXML
	private TableColumn<Ticket, String> ticketUsernameColumn;
	@FXML
	private TableColumn<Ticket, String> ticketIssuerColumn;
	@FXML
	private TableColumn<Ticket, String> ticketStatusColumn;
	@FXML
	private TableColumn<Ticket, String> ticketCreatedAtColumn;
	@FXML
	private TableColumn<Ticket, String> ticketUpdatedAtColumn;

	/**
	 * Search one ticket by id
	 * 
	 * @param actionEvent
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	private void searchTicket(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		try {
			// Get Ticket information
			Ticket ticket = TicketDAO.searchTicket(ticketIdText.getText());
			// Fill Ticket on TableView and Display on TextArea
			fillAndShowTicket(ticket);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error occurred while getting ticket information from DB.\n" + e);
			throw e;
		}
	}

	/**
	 * Search all tickets by keyword, userid, status
	 * 
	 * @param actionEvent
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@FXML
	private void searchTickets(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		try {
			// Get all Tickets information
			ObservableList<Ticket> ticketData = null;
			String keyword = ticketKeywordText.getText();
			String status = searchStatusCombo.getValue();

			if (user.getRole().equals("admin")) {
				ticketData = TicketDAO.searchTickets(keyword, status);
			} else {
				ticketData = TicketDAO.searchTickets(keyword, status, user.getUserId());
			}
			// Fill Tickets on TableView
			fillTickets(ticketData);
		} catch (SQLException e) {
			System.out.println("Error while getting tickets information from DB.\n" + e);
			throw e;
		}
	}

	/**
	 * Called after fxml load
	 */
	@FXML
	public void initialize() {
	}

	/**
	 * Initialize controller
	 * 
	 * @param login
	 * @param user
	 */
	public void init(final Login login, User user) {

		this.user = user;

		ticketIdColumn.setCellValueFactory(cellData -> cellData.getValue().ticketIdProperty().asObject());
		ticketNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		ticketDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		ticketDepartmentColumn.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
		ticketUsernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
		ticketIssuerColumn.setCellValueFactory(cellData -> cellData.getValue().issuerProperty());
		ticketStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		ticketCreatedAtColumn.setCellValueFactory(cellData -> cellData.getValue().createdAtProperty());
		ticketUpdatedAtColumn.setCellValueFactory(cellData -> cellData.getValue().updatedAtProperty());

		// fill department combo box with item choices.
		ObservableList<String> departments = FXCollections.observableArrayList();
		departments.add("Production");
		departments.add("IT");
		departmentCombo.setItems(departments);

		// fill status combo box with item choices.
		ObservableList<String> statuses = FXCollections.observableArrayList();

		statuses.add("Open");
		statuses.add("Processing");
		statuses.add("Closed");
		statusCombo.setItems(statuses);
		statuses.add("");
		searchStatusCombo.setItems(statuses);

		// Add action on table selection
		ticketTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			fillTicketFormInputs(newSelection);
			updateTicketBtn.setVisible(true);
			openTicketBtn.setVisible(true);
		});

		// Add action to show open ticket btn
		openTicketBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					// Get Ticket information
					Ticket ticket = TicketDAO.searchTicket(ticketIdText.getText());
					System.out.println(ticket);
					// Open ticket screen
					login.showOpenTicketView(user, ticket);
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Error while getting ticket information from DB.\n" + e);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Error while getting ticket information from DB.\n" + e);
				}
			}
		});
	}

	/**
	 * Fill Ticket Form Inputs For Update
	 * 
	 * @param ticket
	 */
	private void fillTicketFormInputs(Ticket ticket) {
		// Set each input field value
		if (ticket != null) {
			ticketIdText.setText(Integer.toString(ticket.getTicketId()));
			ticketNameText.setText(ticket.getName());
			ticketDescriptionTextArea.setText(ticket.getDescription());
			departmentCombo.setValue(ticket.getDepartment());
			ticketIssuerText.setText(ticket.getIssuer());
			statusCombo.setValue(ticket.getStatus());
		}
	}

	/**
	 * Fill Ticket
	 * 
	 * @param ticket
	 * @throws ClassNotFoundException
	 */
	private void fillTicket(Ticket ticket) throws ClassNotFoundException {
		ObservableList<Ticket> ticketData = FXCollections.observableArrayList();
		ticketData.add(ticket);
		ticketTable.setItems(ticketData);
	}

	/**
	 * Fill Ticket for TableView and Display Ticket on TextArea
	 * 
	 * @param ticket
	 * @throws ClassNotFoundException
	 */
	private void fillAndShowTicket(Ticket ticket) throws ClassNotFoundException {
		if (ticket != null) {
			fillTicket(ticket);
		} else {
			System.out.println("Ticket not found in database!");
		}
	}

	/**
	 * Fill Tickets for TableView
	 * 
	 * @param ticketData
	 * @throws ClassNotFoundException
	 */
	private void fillTickets(ObservableList<Ticket> ticketData) throws ClassNotFoundException {
		ticketTable.setItems(ticketData);
	}

	/**
	 * Update ticket
	 * @param actionEvent
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@FXML
	private void updateTicket(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		try {
			TicketDAO.updateTicket(ticketIdText.getText(), ticketNameText.getText(),
					ticketDescriptionTextArea.getText(), departmentCombo.getValue(), ticketIssuerText.getText(),
					user.getUserId(), statusCombo.getValue().toString());

			searchTicketsBtn.fire();

			System.out.println("Ticket has been updated. Ticket id: " + ticketIdText.getText());
		} catch (SQLException e) {
			System.out.println("Problem while updating ticket name: " + e);
		}
	}

	/**
	 * Insert ticket to the DB
	 * @param actionEvent
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@FXML
	private void insertTicket(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		try {
			TicketDAO.insertTicket(ticketNameText.getText(), ticketDescriptionTextArea.getText(),
					departmentCombo.getValue().toString(), ticketIssuerText.getText(), user.getUserId(),
					statusCombo.getValue().toString());
			System.out.println("Ticket inserted!");
		} catch (SQLException e) {
			System.out.println("Problem while inserting ticket " + e);
			throw e;
		}
	}

	/**
	 * Delete ticket by Id from DB
	 * @param actionEvent
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@FXML
	private void deleteTicket(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete ticket.");
		alert.setHeaderText("Click OK to confirm you want to delete this ticket.");
		
		Optional<ButtonType> answer = alert.showAndWait();
		if (answer.get() == ButtonType.OK){
			try {
				TicketDAO.deleteTicketWithId(ticketIdText.getText());
				System.out.println("Ticket deleted! Ticket id: " + ticketIdText.getText() + "\n");
			} catch (SQLException e) {
				System.out.println("Problem with deleting ticket! " + e);
				throw e;
			}
		}
		
	
	}

}