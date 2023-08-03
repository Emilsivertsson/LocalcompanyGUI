package com.codeforpizza.robcomgui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class SearchForOrderController {

    OrderService orderService = new OrderService();
    CustomerService customerService = new CustomerService();

    @FXML
    private TextField searchField;

    @FXML
    private ChoiceBox <String> SearchForWhatChoice = new ChoiceBox<>();

    @FXML
    private TableView<Order> allOrdersTabel;

    @FXML
    private TableColumn<Order, Integer> idColum;

    @FXML
    private TableColumn<Order, Integer> orderdByColum;

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
    private Button SearchButton;


    public SearchForOrderController() throws SQLException {
    }


    public void initialize() throws SQLException {
        setAllOrders();
        SearchForWhatChoice.getItems().addAll("date", "fabric", "product");
        SearchForWhatChoice.setValue("date");

    }

    public void setAllOrders() throws SQLException {
        ObservableList<Order> orders = orderService.readAllOrders();

        idColum.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderdByColum.setCellValueFactory(new PropertyValueFactory<>("orderdBy"));
        dateColum.setCellValueFactory(new PropertyValueFactory<>("date"));
        fabricColum.setCellValueFactory(new PropertyValueFactory<>("fabric"));
        productColum.setCellValueFactory(new PropertyValueFactory<>("product"));
        allOrdersTabel.setItems(orders);
    }

    public void search(){

    }

    public void updateOrder(){

    }

    public void deleteOrder(){

    }
}

