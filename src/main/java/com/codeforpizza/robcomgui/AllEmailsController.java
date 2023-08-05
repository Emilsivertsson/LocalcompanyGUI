package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.sql.SQLException;
import java.util.ArrayList;

public class AllEmailsController {

    CustomerService customerService = new CustomerService();
    @FXML
    private TextArea allEmailsTextField;

    public AllEmailsController() throws SQLException {
    }

    public void initialize() throws SQLException {
        ArrayList <String> emails = customerService.readAllEmails();
        for (int i = 0; i < emails.size(); i++) {
            allEmailsTextField.appendText(emails.get(i).toString() + ", \n");
        }
    }

}
