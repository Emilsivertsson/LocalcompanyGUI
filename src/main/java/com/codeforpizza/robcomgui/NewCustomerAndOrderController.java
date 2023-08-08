package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;

public class NewCustomerAndOrderController {

    CustomerService customerService = new CustomerService();

    OrderService orderService = new OrderService();

    @FXML
    private Button saveCustomerAndOrderButton;

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

    //add new customer and order using the textfields
    //if the firstnamn, lastnamn, email or phone is empty, open an alert window "Fältet kan inte vara tomt"
    //if the phone number already exists in the database, open an alert window "Telefonnummer finns redan"
    //if the order date, fabric or product is empty, open an alert window "Fältet kan inte vara tomt"
    @FXML
    public void saveNewCustomerAndOrder() throws SQLException {
        if (FirstnamnField.getText().isEmpty() || LastnamnField.getText().isEmpty() || PhoneField.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Fältet kan inte vara tomt");
            alert.showAndWait();
        } else if (customerService.checkCustomerExistByPhone(Integer.parseInt(PhoneField.getText()))) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Telefonnummer finns redan");
            alert.showAndWait();
        } else if (orderDateField.getText().isEmpty() || fabricfield.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Fältet kan inte vara tomt");
            alert.showAndWait();
        } else {
            Customer customer = new Customer(FirstnamnField.getText(), LastnamnField.getText(), emailField.getText() , Integer.parseInt(PhoneField.getText()));
            customerService.create(customer);
            Order order = new Order(orderDateField.getText(), fabricfield.getText(), productField.getText());
            orderService.create(customer, order);
            saveCustomerAndOrderButton.getScene().getWindow().hide();
        }

    }
}
