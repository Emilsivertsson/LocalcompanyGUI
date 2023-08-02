package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class NewOrderController {

    OrderService orderService = new OrderService();

    @FXML
    private Label customerNameLabel;

    @FXML
    private TextField orderDateField;

    @FXML
    private TextField fabricField;

    @FXML
    private TextField productField;

    @FXML
    private Button saveNewOrderButton;

    private Customer selectedCustomer;

    public NewOrderController() throws SQLException {
    }

    public void initialize() throws SQLException {
        if (selectedCustomer != null) {
            customerNameLabel.setText(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
        } else {
            customerNameLabel.setText("No customer selected.");
        }
    }

    @FXML
    public void saveNewOrder() throws SQLException {
        orderService.create(selectedCustomer, new Order(orderDateField.getText(), fabricField.getText(), productField.getText()));
        saveNewOrderButton.getScene().getWindow().hide();
    }

    // Method to set the customer object from the previous window
    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
}
