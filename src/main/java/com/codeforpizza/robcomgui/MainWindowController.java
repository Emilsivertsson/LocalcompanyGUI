package com.codeforpizza.robcomgui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.sql.SQLException;

public class MainWindowController {

    CustomerService customerService = new CustomerService();

    @FXML
    private Button updateButton;

    @FXML
    private Button addCustomerAndOrderButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button searchForOrderButton;

    @FXML
    private Button allMailsButtons;

    @FXML
    private Button showAllOrdersForSelectedButton;

    @FXML
    private TextField SearchField;

    @FXML
    private ChoiceBox<String> SearchForWhatChoiceBox = new ChoiceBox<>();

    @FXML
    private TableView<Customer> allCustomersTable;

    @FXML
    private TableColumn<Customer, Integer> customerIdIdColum;

    @FXML
    private TableColumn<Customer, String> firstNameColum;

    @FXML
    private TableColumn<Customer, String> lastNameColum;

    @FXML
    private TableColumn<Customer, String> mailColum;

    @FXML
    private TableColumn<Customer, Integer> phoneColum;

    public MainWindowController() throws SQLException {

    }

    public void initialize() throws SQLException {
        printAllCustomers();
        SearchForWhatChoiceBox.getItems().addAll("firstname", "lastname", "phone", "email");
        SearchForWhatChoiceBox.setValue("firstname");
    }


    @FXML
    public void printAllCustomers() throws SQLException {
        ObservableList<Customer> customers = customerService.readAllCustomers();

        customerIdIdColum.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        firstNameColum.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColum.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColum.setCellValueFactory(new PropertyValueFactory<>("phone"));
        mailColum.setCellValueFactory(new PropertyValueFactory<>("email"));


        allCustomersTable.setItems(customers);

    }


    @FXML
    public void addNewCustomerAndOrder() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NewCustomerAndOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Ny kund och order");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void deleteCustomer() throws SQLException {
        Customer customer = allCustomersTable.getSelectionModel().getSelectedItem();
        customerService.delete(customer.getPhone());
        printAllCustomers();
        //TODO add an alert window to confirm the delete
    }

    //open a new window and update selected customer
    @FXML
    public void updateCustomer() throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UpdateCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Uppdatera kund");
        stage.setScene(scene);

        Customer selectedCustomer = allCustomersTable.getSelectionModel().getSelectedItem();
        UpdateCustomerController updateCustomerController = fxmlLoader.getController();
        updateCustomerController.setSelectedCustomer(selectedCustomer);

        updateCustomerController.initialize();
        stage.show();

    }

    //search for a customer by using the search field and the choicebox
    //print the result to the TableView
    @FXML
    public void searchForCustomer() throws SQLException {
        String searchForWhat = SearchForWhatChoiceBox.getValue();
        String searchField = SearchField.getText();
        ObservableList<Customer> customers = customerService.searchForCustomer(searchForWhat, searchField);

        customerIdIdColum.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        firstNameColum.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColum.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColum.setCellValueFactory(new PropertyValueFactory<>("phone"));
        mailColum.setCellValueFactory(new PropertyValueFactory<>("email"));

        allCustomersTable.setItems(customers);
    }

    //open new window and search for an order
    @FXML
    public void searchForOrder() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SearchForOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Sök Order");
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    public void updateTable() throws SQLException {
        printAllCustomers();
    }

    //open a new window and shows all orders of the selected customer
    @FXML
    public void showAllOrders() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllOrdersForSelected.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Alla Ordrar");
        stage.setScene(scene);

        Customer selectedCustomer = allCustomersTable.getSelectionModel().getSelectedItem();
        AllOrdersForSelectedController allOrdersForSelectedController = fxmlLoader.getController();
        allOrdersForSelectedController.setSelectedCustomer(selectedCustomer);
        allOrdersForSelectedController.initialize();

        stage.show();


    }


    @FXML
    public void showAllEmails() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllEmails.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Alla Emails");
        stage.setScene(scene);

        stage.show();
    }
}
