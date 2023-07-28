package com.codeforpizza.robcomgui;

public class Order {

    private int id;
    private String date;

    private String fabric;

    private String product;


    private int orderdBy = 0;

    public Order(String date, String fabric, String product) {
        this.orderdBy = orderdBy;
        this.date = date;
        this.fabric = fabric;
        this.product = product;
    }

    public Order(int id,int orderdBy , String date, String fabric, String product) {
        this.id = id;
        this.orderdBy = orderdBy;
        this.date = date;
        this.fabric = fabric;
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public int getOrderdBy() {
        return orderdBy;
    }

    public void setOrderdBy(int orderdBy) {
        this.orderdBy = orderdBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return  "------------------------" + "\n " +
                "Order Id: " + id + "\n " +
                "Datum: " + date + "\n " +
                "Tyg: " + fabric + "\n " +
                "Produkt: " + product + "\n " +
                "------------------------" +  "\n ";
    }

    public int getCustomerId() {
        return id;
    }
}

