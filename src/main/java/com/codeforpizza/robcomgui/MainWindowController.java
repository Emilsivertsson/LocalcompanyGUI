package com.codeforpizza.robcomgui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class MainWindowController {

    public Button graphButton;
    CustomerService customerService = new CustomerService();

    @FXML
    private Button updateButton;

    @FXML
    private Button addCustomerAndOrderButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button searchForOrderButton;

    @FXML
    private Button allMailsButtons;

    @FXML
    private Button showAllOrdersForSelectedButton;

    @FXML
    private TextField SearchField;

    @FXML
    private ChoiceBox<String> SearchForWhatChoiceBox = new ChoiceBox<>();

    @FXML
    private TableView<Customer> allCustomersTable;

    @FXML
    private TableColumn<Customer, Integer> customerIdIdColum;

    @FXML
    private TableColumn<Customer, String> firstNameColum;

    @FXML
    private TableColumn<Customer, String> lastNameColum;

    @FXML
    private TableColumn<Customer, String> mailColum;

    @FXML
    private TableColumn<Customer, Integer> phoneColum;

    Customer selectedCustomer;

    public MainWindowController() throws SQLException {

    }

    public void initialize() throws SQLException {
        printAllCustomers();
        SearchForWhatChoiceBox.getItems().addAll("firstname", "lastname", "phone", "email");
        SearchForWhatChoiceBox.setValue("firstname");
    }


    @FXML
    public void printAllCustomers() throws SQLException {
        ObservableList<Customer> customers = customerService.readAllCustomers();

        customerIdIdColum.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        firstNameColum.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColum.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColum.setCellValueFactory(new PropertyValueFactory<>("phone"));
        mailColum.setCellValueFactory(new PropertyValueFactory<>("email"));
        allCustomersTable.setItems(customers);
    }


    @FXML
    public void addNewCustomerAndOrder() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NewCustomerAndOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Ny kund och order");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void deleteCustomer() throws SQLException {
        if(allCustomersTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Ingen kund vald");
            alert.showAndWait();
            return;
        }

        selectedCustomer = allCustomersTable.getSelectionModel().getSelectedItem();

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Varning");
        confirmationDialog.setContentText("Är du säkert på att du vill ta bort kunden?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            customerService.delete(selectedCustomer.getPhone());
            printAllCustomers();
        }
    }

    @FXML
    public void updateCustomer() throws IOException {
        if(allCustomersTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Ingen kund vald");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UpdateCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Uppdatera kund");
        stage.setScene(scene);

        selectedCustomer = allCustomersTable.getSelectionModel().getSelectedItem();
        UpdateCustomerController updateCustomerController = fxmlLoader.getController();
        updateCustomerController.setSelectedCustomer(selectedCustomer);

        updateCustomerController.initialize();
        stage.show();

    }

    @FXML
    public void searchForCustomer() throws SQLException {
        String searchForWhat = SearchForWhatChoiceBox.getValue();
        String searchField = SearchField.getText();
        ObservableList<Customer> customers = customerService.searchForCustomer(searchForWhat, searchField);

        customerIdIdColum.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        firstNameColum.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColum.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColum.setCellValueFactory(new PropertyValueFactory<>("phone"));
        mailColum.setCellValueFactory(new PropertyValueFactory<>("email"));

        allCustomersTable.setItems(customers);
    }


    @FXML
    public void searchForOrder() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SearchForOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Sök Order");
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    public void updateTable() throws SQLException {
        printAllCustomers();
    }


    @FXML
    public void showAllOrders() throws Exception {
        if(allCustomersTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Ingen kund vald");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllOrdersForSelected.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Alla Ordrar");
        stage.setScene(scene);

        selectedCustomer = allCustomersTable.getSelectionModel().getSelectedItem();
        AllOrdersForSelectedController allOrdersForSelectedController = fxmlLoader.getController();
        allOrdersForSelectedController.setSelectedCustomer(selectedCustomer);
        allOrdersForSelectedController.initialize();
        allOrdersForSelectedController.printAllOrders(selectedCustomer);

        stage.show();
    }


    @FXML
    public void showAllEmails() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllEmails.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Alla Emails");
        stage.setScene(scene);

        stage.show();
    }

    public void showGraph() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OrdersPerCustomerGraph.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Graf");
        stage.setScene(scene);

        stage.show();
    }
}
