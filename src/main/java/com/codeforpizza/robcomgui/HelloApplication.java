package com.codeforpizza.robcomgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {

    OrderService orderService = new OrderService();



    public HelloApplication() throws SQLException {
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SearchForCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rob & Co. - Customer and Order Management System ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}