package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class UpdateOrderController {

    OrderService orderService = new OrderService();
    @FXML
    private Button updateOrderButton;

    @FXML
    private TextField newDateField;

    @FXML
    private TextField newProductField;

    @FXML
    private TextField newFabricField;

    @FXML
    private Label customerNameLabel;

    private Customer customer;

    private Order order;

    public UpdateOrderController() throws SQLException {
    }

    public void initialize() {
        setOrder(order);
        customerNameLabel.setText(customer.getFirstName() + " " + customer.getLastName());
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    //print orders variables as promts to textfields and update order with new values if entered
    public void updateOrder() throws SQLException {
        if (newDateField.getText() != null) {
            order.setDate(newDateField.getText());
        }
        if (newProductField.getText() != null) {
            order.setProduct(newProductField.getText());
        }
        if (newFabricField.getText() != null) {
            order.setFabric(newFabricField.getText());
        }

        orderService.updateOrder(order);

        updateOrderButton.getScene().getWindow();

    }


}
