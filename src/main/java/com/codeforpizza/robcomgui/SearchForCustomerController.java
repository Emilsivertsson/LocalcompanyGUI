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

public class SearchForCustomerController {

    CustomerService customerService = new CustomerService();

    @FXML
    private Button updateButton;

    @FXML
    private Button addCustomerAndOrderButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button addOrderButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button searchForOrderButton;

    @FXML
    private TextField SearchField;

    @FXML
    private ChoiceBox <String> SearchForWhatChoiceBox = new ChoiceBox<>();

    @FXML
    private TableView <Customer> allCustomersTable;

    @FXML
    private TableColumn <Customer, Integer>  customerIdIdColum;

    @FXML
    private TableColumn <Customer,String> firstNameColum;

    @FXML
    private TableColumn <Customer,String> lastNameColum;

    @FXML
    private TableColumn <Customer,String> mailColum;

    @FXML
    private TableColumn <Customer, Integer> phoneColum;

    public SearchForCustomerController() throws SQLException {

    }

    public void initialize() throws SQLException {
        printAllCustomers();
        SearchForWhatChoiceBox.getItems().addAll("First name", "Last name", "Phone", "Email");
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewCustomerAndOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("New Customer and Order ");
        stage.setScene(scene);
        stage.show();
    }

    //open a new window to add a new order
    @FXML
    public void addNewOrder() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("New Order ");
        stage.setScene(scene);
        stage.show();
    }

    //delete a customer
    @FXML
    public void deleteCustomer() throws SQLException {
        //todo
    }

    //open a new window and update selected customer
    @FXML
    public void updateCustomer() throws SQLException {
        //todo
    }

    //search for a customer by using the search field and the choicebox
    //print the result to the TableView
    //if the searchfield is empty print all customers
    @FXML
    public void searchForCustomer() throws SQLException {
        //todo
    }

    //open new window and search for an order
    @FXML
    public void searchForOrder() throws Exception {
        //todo
    }

    @FXML
    public void updateTable() throws SQLException {
        printAllCustomers();
    }
}
