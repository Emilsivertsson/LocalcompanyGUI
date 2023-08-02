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
    }

    //print all customers in db to TableView
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

    //open a new window to add a new customer and order
    @FXML
    public void addNewCustomerAndOrder() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NewCustomerAndOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("New Customer and Order ");
        stage.setScene(scene);
        stage.show();

    }

    //open a new window to add a new order


    //delete a customer
    @FXML
    public void deleteCustomer() throws SQLException {
        Customer customer = allCustomersTable.getSelectionModel().getSelectedItem();
        customerService.delete(customer.getPhone());
        printAllCustomers();
        //todo
        //add a confirmation window
    }

    //open a new window and update selected customer
    @FXML
    public void updateCustomer() throws SQLException {
        //todo
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
        stage.setTitle("Search for Order");
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
        stage.setTitle("All Orders");
        stage.setScene(scene);

        Customer selectedCustomer = allCustomersTable.getSelectionModel().getSelectedItem();
        AllOrdersForSelectedController controller = fxmlLoader.getController();
        controller.setSelectedCustomer(selectedCustomer);
        controller.initialize();

        stage.show();

        //TODO not working
    }

    //open a new window and shows all emails in the db
    @FXML
    public void showAllEmails() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllEmails.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("All Emails");
        stage.setScene(scene);

        stage.show();
    }
}
