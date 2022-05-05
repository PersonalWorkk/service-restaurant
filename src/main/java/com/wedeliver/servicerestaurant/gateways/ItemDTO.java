package com.wedeliver.servicerestaurant.gateways;

import com.wedeliver.servicerestaurant.domain.Item;
import com.wedeliver.servicerestaurant.domain.Restaurant;

public class ItemDTO {
    private String name;
    private Float price;
    public ItemDTO(){}
    public ItemDTO(String name, Float price){
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public Float getPrice() {
        return price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public Item convert2Item(Restaurant restaurant){
        return new Item(this.name, this.price, restaurant);
    }
}
