package com.codeforpizza.robcomgui;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrdersPerCustomerController {

    CustomerService customerService = new CustomerService();
    OrderService orderService = new OrderService();

    @FXML
    public NumberAxis barChartNumA;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    public CategoryAxis barchartCatA;

    public OrdersPerCustomerController() throws SQLException {
    }



    //TODO graph works, but is inverted.
    @FXML
    public void initialize() throws SQLException {
        Map<Integer, Integer> ordersCountMap = new HashMap<>();

        // Count the number of orders per customer
        for (Customer customer : customerService.readAllCustomers()) {
            int numberOfOrders = orderService.readAllOrdersForCustomer(customer).size();

            if (numberOfOrders > 0) {
                //if the amount of orders is already a key in the map, add 1 to the value
                if (ordersCountMap.containsKey(numberOfOrders)) {
                    ordersCountMap.put(numberOfOrders, ordersCountMap.get(numberOfOrders) + 1);
                } else {
                    //if the amount of orders is not a key in the map, add it as a key and set the value to 1
                    ordersCountMap.put(numberOfOrders, 1);
                }

            }
            //print the map
            System.out.println(ordersCountMap);
        }

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Number of Customers");

        //for each key:value pair in the map, add a new data point to the chart
        // using the key as the x value and the value as the y value
        for (Map.Entry<Integer, Integer> entry : ordersCountMap.entrySet()) {
            dataSeries.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
        }


        barChart.getData().add(dataSeries);
    }
}
