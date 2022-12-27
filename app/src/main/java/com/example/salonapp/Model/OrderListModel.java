package com.example.salonapp.Model;

public class OrderListModel {

    public String customerName;

    public OrderListModel() {
    }

    public OrderListModel(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
