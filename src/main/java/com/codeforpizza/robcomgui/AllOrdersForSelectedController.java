package com.codeforpizza.robcomgui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

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
    private Button updateOrdersButton;

    private Customer selectedCustomer;

    public AllOrdersForSelectedController() throws SQLException {
    }

    public void initialize() throws SQLException {
        setSelectedCustomer(selectedCustomer);
        if (selectedCustomer != null) {
            customerNameLable.setText(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
        } else {
            customerNameLable.setText("No customer selected");
        }
        ObservableList<Order> orders = orderService.read(selectedCustomer.getCustomerId());

        IdColum.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderId"));
        dateColum.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        fabricColum.setCellValueFactory(new PropertyValueFactory<Order, String>("fabric"));
        productColum.setCellValueFactory(new PropertyValueFactory<Order, String>("product"));
        allOrdersTable.setItems(orders);
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public void updateOrder() throws SQLException {
        Order selectedOrder = allOrdersTable.getSelectionModel().getSelectedItem();
    }

    public void deleteOrder() throws SQLException {
        Order selectedOrder = allOrdersTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void addNewOrder() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NewOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("New Order");
        stage.setScene(scene);


        NewOrderController controller = fxmlLoader.getController();

        controller.setSelectedCustomer(selectedCustomer);

        controller.initialize();
        stage.show();
    }
}
