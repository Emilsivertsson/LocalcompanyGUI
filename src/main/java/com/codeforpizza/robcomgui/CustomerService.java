package com.codeforpizza.robcomgui;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerService {

    Database db = new Database("Customers");

    public CustomerService() throws SQLException {
    }

    public void checkIfConnection() throws SQLException {
        if (db.checkConnection()) {
            db.openConnection("Customers");
        }
    }

    public void closeConnection() throws SQLException {
        db.closeConnection();
    }

    public boolean checkCustomerExistByEmail(String email) throws SQLException {
        checkIfConnection();
        boolean exists = db.checkIfCustomerExistByEmail(email);
        closeConnection();
        return exists;
    }

    public boolean checkCustomerExistByPhone(int phoneNumber) throws SQLException {
        checkIfConnection();
        boolean exists = db.checkIfCustomerExistByPhone(phoneNumber);
        closeConnection();
        return exists;
    }

    public void create(Customer customer) throws SQLException {
        checkIfConnection();
        db.createCustomer(customer);
        closeConnection();
    }


    public ObservableList<Customer> readAllCustomers() throws SQLException {
        checkIfConnection();
        ObservableList<Customer> customers = db.readAllCustomers();
        closeConnection();
        return customers;
    }

    public void delete(int phone) throws SQLException {
        checkIfConnection();
        db.deleteCustomer(phone);
        closeConnection();
    }

    public void update(Customer customer) throws SQLException {
        checkIfConnection();
        db.updateCustomer(customer);
        closeConnection();
    }

    public ArrayList <String> readAllEmails() throws SQLException {
        checkIfConnection();
        ArrayList <String> emails = db.readAllEmails();
        closeConnection();
        return emails;

    }

    public ObservableList<Customer> searchForCustomer(String searchForWhat, String searchField) throws SQLException {
        checkIfConnection();
        ObservableList<Customer> customers = db.searchForCustomer(searchForWhat, searchField);
        closeConnection();
        return customers;
    }
}
