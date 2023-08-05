package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class NewCustomerAndOrderController {

    CustomerService customerService = new CustomerService();

    OrderService orderService = new OrderService();

    @FXML
    private Button SaveCustomerAndOrderButton;

    @FXML
    private TextField FirstnamnField;

    @FXML
    private TextField LastnamnField;

    @FXML
    private TextField  PhoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField orderDateField;

    @FXML
    private TextField fabricfield;

    @FXML
    private TextField productField;


    public NewCustomerAndOrderController() throws SQLException {
    }


    @FXML
    public void saveNewCustomerAndOrder() throws SQLException {
        Customer customer = new Customer(FirstnamnField.getText(), LastnamnField.getText(), emailField.getText(), Integer.parseInt(PhoneField.getText()));
        customerService.create(customer);
        Order order = new Order(orderDateField.getText(), fabricfield.getText(), productField.getText());
        orderService.create(customer, order);
        SaveCustomerAndOrderButton.getScene().getWindow().hide();

    }
}
