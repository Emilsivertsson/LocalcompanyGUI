package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SearchForCustomerController {
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
    private TableColumn IdColum;

    @FXML
    private TableColumn firstNameColum;

    @FXML
    private TableColumn lastNameColum;

    @FXML
    private TableColumn mailColum;

    @FXML
    private TableColumn phoneColum;
}
