package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class SearchForCustomerController {

    CustomerService customerService = new CustomerService();

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
    private ChoiceBox SearchForWhatChoiceBox;

    @FXML
    private TableView allCustomersTable;

    @FXML
    private TableColumn  IdColum;

    @FXML
    private TableColumn firstNameColum;

    @FXML
    private TableColumn lastNameColum;

    @FXML
    private TableColumn mailColum;

    @FXML
    private TableColumn phoneColum;

    public SearchForCustomerController() throws SQLException {
    }

    //print all customers in db to TableView
    @FXML
    public void printAllCustomers() throws SQLException {
        allCustomersTable.setItems(customerService.readAllCustomers());

    }

    //open a new window to add a new customer and order
    @FXML
    public void addNewCustomerAndOrder() throws Exception {
        //Todo
    }

    //open a new window to add a new order
    @FXML
    public void addNewOrder() throws Exception {
        //todo
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
}
