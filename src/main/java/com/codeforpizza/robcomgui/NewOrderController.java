package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewOrderController {

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
}
