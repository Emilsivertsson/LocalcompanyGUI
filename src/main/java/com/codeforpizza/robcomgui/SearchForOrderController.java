package com.codeforpizza.robcomgui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class SearchForOrderController {

    OrderService orderService = new OrderService();
    CustomerService customerService = new CustomerService();

    @FXML
    private TextField searchField;

    @FXML
    private ChoiceBox<String> SearchForWhatChoice = new ChoiceBox<>();

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

    public void search() throws SQLException {
        String searchForWhat = SearchForWhatChoice.getValue();
        String searchFor = searchField.getText();
        ObservableList<Order> orders = orderService.search(searchForWhat, searchFor);

        idColum.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderdByColum.setCellValueFactory(new PropertyValueFactory<>("orderdBy"));
        dateColum.setCellValueFactory(new PropertyValueFactory<>("date"));
        fabricColum.setCellValueFactory(new PropertyValueFactory<>("fabric"));
        productColum.setCellValueFactory(new PropertyValueFactory<>("product"));
        allOrdersTabel.setItems(orders);

    }

    public void updateOrder() throws IOException, SQLException {
        if(allOrdersTabel.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Ingen order vald");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UpdateOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("update order");
        stage.setScene(scene);

        UpdateOrderController updateOrderController = fxmlLoader.getController();
        updateOrderController.setOrder(allOrdersTabel.getSelectionModel().getSelectedItem());

        updateOrderController.initialize(customerService.readById(allOrdersTabel.getSelectionModel().getSelectedItem().getOrderdBy()));
        stage.show();

    }

    //delete the order the user has chosen.
    public void deleteOrder() throws SQLException {
        if(allOrdersTabel.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varning");
            alert.setHeaderText("Ingen order vald");
            alert.showAndWait();
            return;
        }

        Order selectedOrder = allOrdersTabel.getSelectionModel().getSelectedItem();

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Varning");
        confirmationDialog.setContentText("Är du säkert på att du vill ta bort denna ordern?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            orderService.delete(selectedOrder.getOrderId());
            setAllOrders();
        }

    }

    public void UpdatelOrderlist(ActionEvent actionEvent) throws SQLException {
        setAllOrders();

    }
}


