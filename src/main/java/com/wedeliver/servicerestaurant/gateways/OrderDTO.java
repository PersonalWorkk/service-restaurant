package com.wedeliver.servicerestaurant.gateways;

import java.util.List;

import com.wedeliver.servicerestaurant.domain.Item;
import com.wedeliver.servicerestaurant.domain.Restaurant;

public class OrderDTO {
    private String clientFirstName;
    private String clientLastName;
    private String deliveryAddress;
    private Float totalPrice;
    private List<Item> items;
    private Restaurant restaurant;

    public OrderDTO(){}
    public OrderDTO(String clientFirstName, String clientLastName, String deliveryAddres, List<Item> items){
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.deliveryAddress = deliveryAddres;
        this.items = items;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }
    public String getClientLastName() {
        return clientLastName;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public List<Item> getItems() {
        return items;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }
    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void calculateTotalPrice(){
        Float totalPrice = (float) 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        // set the total order price
        this.setTotalPrice(totalPrice);
    }
}
