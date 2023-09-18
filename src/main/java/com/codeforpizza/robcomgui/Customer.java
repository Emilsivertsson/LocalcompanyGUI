package com.codeforpizza.robcomgui;

import java.util.ArrayList;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private ArrayList<Order> orders;

    public Customer(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        if(phone.isEmpty()){
            throw new IllegalArgumentException("Telefonnummer måste vara större än 0");
        }
        this.orders = new ArrayList<>();
    }

    public Customer(int customerId, String firstName, String lastName, String email, String phone) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        if(phone.isEmpty()){
            throw new IllegalArgumentException("Telefonnummer måste vara större än 0");
        }
        this.orders = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone.length() <= 0){
            throw new IllegalArgumentException("Telefonnummer måste vara större än 0");
        }
        this.phone = phone;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return  "-----------------------------------\n" +
                "id: " + customerId + '\n' +
                "Förnamn: " + firstName + '\n' +
                "Efternamn: " + lastName + '\n' +
                "Email: " + email + '\n' +
                "Telefon: " + phone + '\n' +
                "-----------------------------------\n";
    }

}
