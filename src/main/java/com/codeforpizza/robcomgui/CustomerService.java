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
    public Customer readCustomerById(int id) throws SQLException {
        checkIfConnection();
        Customer customer = db.readOneCustomerByEmail(id);
        closeConnection();
        return customer;
    }

    public Customer readCustomerByEmail(String email) throws SQLException {
        checkIfConnection();
        Customer customer = db.readOneCustomerByEmail(email);
        closeConnection();
        return customer;
    }

    public Customer readCustomerByPhone(int phone) throws SQLException {
        checkIfConnection();
        Customer customer = db.readOneCustomerByPhone(phone);
        closeConnection();
        return customer;
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

    public ArrayList<Customer> searchByFirstName(String firstName) throws SQLException {
        checkIfConnection();
        ArrayList<Customer> customers = db.searchByFirstName(firstName);
        closeConnection();
        return customers;
    }

    public ArrayList<Customer> searchByLastName(String lastName) throws SQLException {
        checkIfConnection();
        return db.searchByLastName(lastName);
    }

    public ArrayList<Customer> searchByEmail(String email) throws SQLException {
        checkIfConnection();
        ArrayList<Customer> customers = db.searchByEmail(email);
        closeConnection();
        return customers;
    }

    public ArrayList<Customer> searchByPhone(int phoneNumber) throws SQLException {
        checkIfConnection();
        ArrayList<Customer> customers = db.searchByPhone(phoneNumber);
        closeConnection();
        return customers;
    }

    public String readAllEmails() throws SQLException {
        checkIfConnection();
        String emails = db.readAllEmails();
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
