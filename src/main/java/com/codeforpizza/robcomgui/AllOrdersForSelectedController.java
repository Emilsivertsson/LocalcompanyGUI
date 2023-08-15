package com.codeforpizza.robcomgui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AllOrdersForSelectedController {

    OrderService orderService = new OrderService();

    @FXML
    private Label customerNameLable;

    @FXML
    private TableView<Order> allOrdersTable;

    @FXML
    private TableColumn<Order, Integer> IdColum;

    @FXML
    private TableColumn<Order, String> dateColum;

    @FXML
    private TableColumn<Order, String> fabricColum;

    @FXML
    private TableColumn<Order, String> productColum;

    @FXML
    private Button updateOrderButton;

    @FXML
    private Button deleteOrderButton;

    @FXML
    private Button newOrderButton;

    @FXML
    private Button updateListButton;

    Customer selectedCustomer;

    public AllOrdersForSelectedController() throws SQLException {
    }

    public void initialize() throws SQLException {

        if (selectedCustomer != null) {
            customerNameLable.setText(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
            printAllOrders(selectedCustomer);
        } else {
            customerNameLable.setText("Ingen kund");
        }

    }

    public void updateList () throws SQLException {
        printAllOrders(selectedCustomer);
    }

    public void printAllOrders(Customer selectedCustomer) throws SQLException {
        ObservableList<Order> allOrders = orderService.read(selectedCustomer.getCustomerId());

        IdColum.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        dateColum.setCellValueFactory(new PropertyValueFactory<>("date"));
        fabricColum.setCellValueFactory(new PropertyValueFactory<>("fabric"));
        productColum.setCellValueFactory(new PropertyValueFactory<>("product"));
        allOrdersTable.setItems(allOrders);
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public void updateOrder() throws SQLException, IOException {
        if(allOrdersTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Ingen order vald");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UpdateOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Uppdatera Order");
        stage.setScene(scene);

        UpdateOrderController controller = fxmlLoader.getController();
        controller.setOrder(allOrdersTable.getSelectionModel().getSelectedItem());
        controller.initialize(selectedCustomer);
        stage.show();
        printAllOrders(selectedCustomer);
    }


    public void deleteOrder() throws SQLException {
        if(allOrdersTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Ingen order vald");
            alert.showAndWait();
            return;
        }

        Order selectedOrder = allOrdersTable.getSelectionModel().getSelectedItem();
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Varning");
        confirmationDialog.setContentText("Är du säkert på att du vill ta bort denna order?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            orderService.delete(selectedOrder.getOrderId());
            printAllOrders(selectedCustomer);
        }
    }

    public void addNewOrder() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NewOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Ny Order");
        stage.setScene(scene);

        NewOrderController controller = fxmlLoader.getController();

        controller.setSelectedCustomer(selectedCustomer);

        controller.initialize();
        stage.show();
        printAllOrders(selectedCustomer);
    }
}
