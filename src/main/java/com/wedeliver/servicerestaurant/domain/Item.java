package com.wedeliver.servicerestaurant.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Float price;
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    public Item(){}
    public Item(String name, Float price, Restaurant restaurant){
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Float getPrice() {
        return price;
    }
    public Long getRestaurantId() {
        return restaurant.getId();
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
}
