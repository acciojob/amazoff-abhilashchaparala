package com.driver.model;

public class Order {

    private String id;
    private final int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        int HH = Integer.parseInt(deliveryTime.substring(0,1));
        int MM = Integer.parseInt(deliveryTime.substring(3));
        this.deliveryTime  = HH*60 + MM;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
