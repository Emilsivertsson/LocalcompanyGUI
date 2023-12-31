package com.codeforpizza.robcomgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Database {


    Connection conn = null;

    public Database (String DBName) throws SQLException {
        openConnection(DBName);
        createTables();

    }


    public void openConnection(String DBName) throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + DBName + ".db");
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public boolean checkConnection() throws SQLException {
        try {
            return conn.isClosed();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }



    public void createTables() throws SQLException {

        String customers = "CREATE TABLE IF NOT EXISTS customers " +
                "(customerId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname VARCHAR (50) not null ," +
                "lastname VARCHAR (50) not null ," +
                "email VARCHAR (150) unique," +
                "phone VARCHAR(50) unique not null," +
                "orderId INTEGER," +
                "FOREIGN KEY (orderId) REFERENCES orders (orderdBy))";

        String orders = "CREATE TABLE IF NOT EXISTS orders " +
                "(orderId INTEGER PRIMARY KEY, " +
                "orderdBy INTEGER not null , " +
                "date LocalDate not null ," +
                "fabric VARCHAR (50) not null ," +
                "product VARCHAR (150))";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(customers);
            stmt.execute(orders);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public boolean checkIfCustomerExistByEmail(String email) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM customers WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return true;
    }

    public boolean checkIfCustomerExistByPhone(String phoneNumber) {
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM customers WHERE phone = ?");
            stmt.setString(1, phoneNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void createCustomer(Customer customer) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers (firstname, lastname, email, phone, orderId) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setInt(5, customer.getCustomerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Customer readCustomerById(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE customerId = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Customer customer = new Customer(rs.getInt("customerId")
                    , rs.getString("firstname")
                    , rs.getString("lastname")
                    , rs.getString("email")
                    , rs.getString("phone"));

            PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM orders WHERE orderdBy = ?");
            stmt2.setInt(1, customer.getCustomerId());
            ResultSet rs2 = stmt2.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();
            while (rs2.next()) {
                orders.add(new Order(rs2.getInt("orderId")
                        , rs2.getInt("orderdBy")
                        , rs2.getDate("date").toLocalDate()
                        , rs2.getString("fabric")
                        , rs2.getString("product")));
            }
            customer.setOrders(orders);

            return customer;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public Customer readOneCustomerByPhone(String phone) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE phone = ?");
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            Customer customer = new Customer(rs.getInt("customerId")
                    , rs.getString("firstname")
                    , rs.getString("lastname")
                    , rs.getString("email")
                    , rs.getString("phone"));

            PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM orders WHERE orderdBy = ?");
            stmt2.setInt(1, customer.getCustomerId());
            ResultSet rs2 = stmt2.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();
            while (rs2.next()) {
                orders.add(new Order(rs2.getInt("orderId")
                        , rs2.getInt("orderdBy")
                        , rs2.getDate("date").toLocalDate()
                        , rs2.getString("fabric")
                        , rs2.getString("product")));
            }
            customer.setOrders(orders);

            return customer;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    //read all customers returns a ObservableList of customers
    public ObservableList<Customer> readAllCustomers() throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers");
            ResultSet rs = stmt.executeQuery();
            ObservableList<Customer> customers = FXCollections.observableArrayList();
            while (rs.next()) {
                customers.add(new Customer(rs.getInt("customerId")
                        , rs.getString("firstname")
                        , rs.getString("lastname")
                        , rs.getString("email")
                        , rs.getString("phone")));
            }
            return customers;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void deleteCustomer(String phoneNr) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM orders WHERE orderdBy = ?");
            stmt.setInt(1, readOneCustomerByPhone(phoneNr).getCustomerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM customers WHERE phone = ?");
            stmt.setString(1, phoneNr);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }


    public void updateCustomer(Customer customer) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET firstname = ?, lastname = ?, email = ?, phone = ? WHERE customerId = ?");
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setInt(5, customer.getCustomerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public void createOrder(Customer customer, Order order) throws SQLException {
        try {
            Customer customerWithID = readCustomerByPhone(customer.getPhone());
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO orders (orderdBy, date, fabric, product) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, customerWithID.getCustomerId());
            stmt.setDate(2, Date.valueOf(order.getDate()));
            stmt.setString(3, order.getFabric());
            stmt.setString(4, order.getProduct());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    //read all orders returns a ObservableList of orders
    public ObservableList<Order> readAllOrdersForSelected(int id) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE orderdBy = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            ObservableList<Order> orders = FXCollections.observableArrayList();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("orderId")
                        , rs.getDate("date").toLocalDate()
                        , rs.getString("fabric")
                        , rs.getString("product")));
            }
            return orders;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    //read all orders returns a ObservableList of orders
    public ObservableList<Order> readAllOrders() throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders");
            ResultSet rs = stmt.executeQuery();
            ObservableList<Order> orders = FXCollections.observableArrayList();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("orderId")
                        , rs.getInt("orderdBy")
                        , rs.getDate("date").toLocalDate()
                        , rs.getString("fabric")
                        , rs.getString("product")));
            }
            return orders;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void deleteOrder(int id) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM orders WHERE orderId = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void updateOrder(Order order) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE orders SET orderdBy = ?, date = ?, fabric = ?, product = ? WHERE orderId = ?");
            stmt.setInt(1, order.getCustomerId());
            stmt.setDate(2, Date.valueOf(order.getDate()));
            stmt.setString(3, order.getFabric());
            stmt.setString(4, order.getProduct());
            stmt.setInt(5, order.getOrderId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }




    //return a list of all the emails in the database
    public ArrayList <String> readAllEmails() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT email FROM customers");
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> emails = new ArrayList<>();
            while (rs.next()) {
                emails.add(rs.getString("email"));
            }
            return emails;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ObservableList<Customer> searchForCustomer(String searchForWhat, String searchField) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE " + searchForWhat + " LIKE ?");
            stmt.setString(1, "%" + searchField + "%");
            ResultSet rs = stmt.executeQuery();
            ObservableList<Customer> customers = FXCollections.observableArrayList();
            while (rs.next()) {
                customers.add(new Customer(rs.getInt("customerId")
                        , rs.getString("firstname")
                        , rs.getString("lastname")
                        , rs.getString("email")
                        , rs.getString("phone")));
            }
            return customers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ObservableList<Order> search(String searchForWhat, String searchFor) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE " + searchForWhat + " LIKE ?");
            stmt.setString(1, "%" + searchFor + "%");
            ResultSet rs = stmt.executeQuery();
            ObservableList<Order> orders = FXCollections.observableArrayList();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("orderId")
                        , rs.getInt("orderdBy")
                        , rs.getDate("date").toLocalDate()
                        , rs.getString("fabric")
                        , rs.getString("product")));
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Collection<Object> readAllOrdersForCustomer(Customer customer) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE orderdBy = ?");
            stmt.setInt(1, customer.getCustomerId());
            ResultSet rs = stmt.executeQuery();
            Collection<Object> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("orderId")
                        , rs.getInt("orderdBy")
                        , rs.getDate("date").toLocalDate()
                        , rs.getString("fabric")
                        , rs.getString("product")));
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Customer readCustomerByPhone(String phone) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE phone = ?");
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            Customer customer = new Customer(rs.getInt("customerId")
                    , rs.getString("firstname")
                    , rs.getString("lastname")
                    , rs.getString("email")
                    , rs.getString("phone"));

            PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM orders WHERE orderdBy = ?");
            stmt2.setInt(1, customer.getCustomerId());
            ResultSet rs2 = stmt2.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();
            while (rs2.next()) {
                orders.add(new Order(rs2.getInt("orderId")
                        , rs2.getInt("orderdBy")
                        , rs2.getDate("date").toLocalDate()
                        , rs2.getString("fabric")
                        , rs2.getString("product")));
            }
            customer.setOrders(orders);

            return customer;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
