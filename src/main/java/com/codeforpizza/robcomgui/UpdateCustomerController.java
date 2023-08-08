package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class UpdateCustomerController {

    CustomerService customerService = new CustomerService();


    @FXML
    private TextField newPhoneLabel;

    @FXML
    private TextField newLastNameLabel;

    @FXML
    private TextField newFirstNameLabel;

    @FXML
    private TextField newEmailLabel;

    @FXML
    private Button updateButton;


    Customer selectedCustomer;

    public UpdateCustomerController() throws SQLException {
    }

    public void initialize() {
        setSelectedCustomer(selectedCustomer);
        if(selectedCustomer != null){
            newFirstNameLabel.setText(selectedCustomer.getFirstName());
            newLastNameLabel.setText(selectedCustomer.getLastName());
            newEmailLabel.setText(selectedCustomer.getEmail());
            newPhoneLabel.setText(String.valueOf(selectedCustomer.getPhone()));
            }
        else{
            newFirstNameLabel.setPromptText("Ingen kund vald");
        }
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }


    public void updateCustomer() throws SQLException {
        if (newFirstNameLabel.getText().isEmpty() || newLastNameLabel.getText().isEmpty() || newPhoneLabel.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Fältet kan inte vara tomt");
            alert.showAndWait();
        } else if (customerService.checkCustomerExistByPhone(Integer.parseInt(newPhoneLabel.getText())) && !selectedCustomer.getFirstName().equals(newFirstNameLabel.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Telefonnummer finns redan");
            alert.showAndWait();
        } else if (customerService.checkCustomerExistByEmail(newEmailLabel.getText()) && !selectedCustomer.getFirstName().equals(newFirstNameLabel.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Email finns redan");
            alert.showAndWait();
        } else {
            selectedCustomer.setFirstName(newFirstNameLabel.getText());
            selectedCustomer.setLastName(newLastNameLabel.getText());
            selectedCustomer.setEmail(newEmailLabel.getText());
            selectedCustomer.setPhone(Integer.parseInt(newPhoneLabel.getText()));
            customerService.update(selectedCustomer);
            updateButton.getScene().getWindow().hide();
        }
    }
}
