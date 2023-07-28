package com.codeforpizza.robcomgui;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean checkOrderExist(int id) throws SQLException {
        checkIfConnection();
        boolean exists = db.checkIfOrderExist(id);
        closeConnection();
        return exists;
    }

    public boolean checkOrdersExist(Customer customer) throws SQLException {
        checkIfConnection();
        boolean exists = db.checkOrdersExist(customer);
        closeConnection();
        return exists;
    }


    public void create(Customer customer, Order order) throws SQLException {
        checkIfConnection();
        db.createOrder(customer, order);
        closeConnection();
    }

    public Order read(int id) throws SQLException {
        checkIfConnection();
        Order order = db.readOneOrder(id);
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

    public String readAllOrders() throws SQLException {
        checkIfConnection();
        String orders = db.readAllOrders();
        closeConnection();
        return orders;
    }

    public ArrayList<Order> searchByFabric(String fabric) throws SQLException {
        checkIfConnection();
        ArrayList <Order> orders = db.searchByFabric(fabric);
        closeConnection();
        return orders;
    }

    public ArrayList<Order> searchByDate(String date) throws SQLException {
        checkIfConnection();
        ArrayList <Order> orders = db.searchByDate(date);
        closeConnection();
        return orders;
    }

    public ArrayList<Order> searchByProduct(String product) throws SQLException {
        checkIfConnection();
        ArrayList <Order> orders = db.searchByProduct(product);
        closeConnection();
        return orders;
    }
}
