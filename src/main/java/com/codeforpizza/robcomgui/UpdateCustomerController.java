package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
            newFirstNameLabel.setPromptText(selectedCustomer.getFirstName());
            newLastNameLabel.setPromptText(selectedCustomer.getLastName());
            newPhoneLabel.setPromptText(String.valueOf(selectedCustomer.getPhone()));
            newEmailLabel.setPromptText(selectedCustomer.getEmail());
            }
        else{
            newFirstNameLabel.setPromptText("Ingen kund vald");
        }
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }


    //TODO not working
    public void updateCustomer() throws SQLException {
        String newFirstNameText = newFirstNameLabel.getText();
        if (!newFirstNameText.isEmpty() && !newFirstNameText.equals(selectedCustomer.getFirstName())) {
            selectedCustomer.setFirstName(newFirstNameText);
        }
        String newLastNameText = newLastNameLabel.getText();
        if (!newLastNameText.isEmpty() && !newLastNameText.equals(selectedCustomer.getLastName())) {
            selectedCustomer.setLastName(newLastNameText);
        }
        String newEmailText = newEmailLabel.getText();
        if (!newEmailText.isEmpty() && !newEmailText.equals(selectedCustomer.getEmail())) {
            selectedCustomer.setEmail(newEmailText);
        }
        String newPhoneText = newPhoneLabel.getText();
        if (!newPhoneText.isEmpty() && !newPhoneText.equals(String.valueOf(selectedCustomer.getPhone()))) {
            selectedCustomer.setPhone(Integer.parseInt(newPhoneText));
        }
        if (customerService.checkCustomerExistByEmail(newEmailText)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Email redan i användning");
            alert.setHeaderText(null);
            alert.setContentText("Det här Email finns redan");
            alert.showAndWait();
        }
        else {
            if (newPhoneText.isEmpty() || !newPhoneText.equals(String.valueOf(selectedCustomer.getPhone()))) {
                if (customerService.checkCustomerExistByPhone(Integer.parseInt(newPhoneText))) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Telefonnummer redan i användning");
                    alert.setHeaderText(null);
                    alert.setContentText("Det här telefonnummer finns redan");
                    alert.showAndWait();
                } else {
                    customerService.update(selectedCustomer);
                    updateButton.getScene().getWindow().hide();
                }
            } else {
                customerService.update(selectedCustomer);
                updateButton.getScene().getWindow().hide();
            }

        }

    }
}
