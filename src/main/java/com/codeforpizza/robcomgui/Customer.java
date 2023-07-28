package com.codeforpizza.robcomgui;

import java.util.ArrayList;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private ArrayList<Order> orders;

    public Customer(String firstName, String lastName, String email, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        if(phoneNumber <= 0){
            throw new IllegalArgumentException("Telefonnummer måste vara större än 0");
        }
        this.orders = new ArrayList<>();
    }

    public Customer(int id, String firstName, String lastName, String email, int phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        if(phoneNumber <= 0){
            throw new IllegalArgumentException("Telefonnummer måste vara större än 0");
        }
        this.orders = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        if(phoneNumber <= 0){
            throw new IllegalArgumentException("Telefonnummer måste vara större än 0");
        }
        this.phoneNumber = phoneNumber;
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
                "id: " + id + '\n' +
                "Förnamn: " + firstName + '\n' +
                "Efternamn: " + lastName + '\n' +
                "Email: " + email + '\n' +
                "Telefon: " + phoneNumber + '\n' +
                "-----------------------------------\n";
    }

}
