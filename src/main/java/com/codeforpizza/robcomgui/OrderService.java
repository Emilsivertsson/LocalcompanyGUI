package com.codeforpizza.robcomgui;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Collection;

public class OrderService {

    Database db = new Database("Customers");

    public OrderService() throws SQLException {
    }

    public void checkIfConnection() throws SQLException {
        if (db.checkConnection()) {
            db.openConnection("Customers");
        }
    }

    public void closeConnection() throws SQLException {
        db.closeConnection();
    }

    public void create(Customer customer, Order order) throws SQLException {
            checkIfConnection();
            db.createOrder(customer, order);
            closeConnection();
    }

    public ObservableList<Order> read(int id) throws SQLException {
        checkIfConnection();
        ObservableList<Order> order = db.readAllOrdersForSelected(id);
        closeConnection();
        return order;
    }

    public void delete(int id) throws SQLException {
        checkIfConnection();
        db.deleteOrder(id);
        closeConnection();
    }

    public void updateOrder(Order order) throws SQLException {
        checkIfConnection();
        db.updateOrder(order);
        closeConnection();
    }

    public ObservableList<Order> readAllOrders() throws SQLException {
        checkIfConnection();
        ObservableList<Order> orders = db.readAllOrders();
        closeConnection();
        return orders;
    }

    public ObservableList<Order> search(String searchForWhat, String searchFor) throws SQLException {
        checkIfConnection();
        ObservableList<Order> orders = db.search(searchForWhat, searchFor);
        closeConnection();
        return orders;
    }

    public Collection<Object> readAllOrdersForCustomer(Customer customer) throws SQLException {
        checkIfConnection();
        Collection<Object> orders = db.readAllOrdersForCustomer(customer);
        closeConnection();
        return orders;
    }
}
