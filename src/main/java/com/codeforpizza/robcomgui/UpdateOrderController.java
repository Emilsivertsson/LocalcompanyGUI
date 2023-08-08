package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

    Customer SelectedCustomer;

    Order order;

    public UpdateOrderController() throws SQLException {
    }


    public void initialize(Customer customer) {
        setSelectedCustomer(customer);
        setOrder(order);
        customerNameLabel.setText(SelectedCustomer.getFirstName() + " " + SelectedCustomer.getLastName());
        newDateField.setText(order.getDate());
        newProductField.setText(order.getProduct());
        newFabricField.setText(order.getFabric());
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.SelectedCustomer = selectedCustomer;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void updateOrder() throws SQLException {
        if (newFabricField.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Fabric");
            alert.setHeaderText("Tyg-nummer kan inte vara tomt");
            alert.showAndWait();
        } else {
            if (newDateField.getText().isEmpty()) {
                order.setDate(order.getDate());
            } else {
                order.setDate(newDateField.getText());
            }
            if (newProductField.getText().isEmpty()) {
                order.setProduct(order.getProduct());
            } else {
                order.setProduct(newProductField.getText());
            }
            if (newFabricField.getText().isEmpty()) {
                order.setFabric(order.getFabric());
            } else {
                order.setFabric(newFabricField.getText());
            }
            orderService.updateOrder(order);
            updateOrderButton.getScene().getWindow().hide();
        }
    }


}
