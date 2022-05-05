package com.wedeliver.servicerestaurant.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String city;
    private String address;
    private String postalCode;
    private String workingHours; // maybe use Time type
    private String phoneNumber;
    @OneToMany(mappedBy = "restaurant")
    private Set<Item> items;

    public Restaurant(){}
    public Restaurant(String name, String city, String address, String postalCode, String workingHours, String phoneNumber){
        this.name = name;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.workingHours = workingHours;
        this.phoneNumber = phoneNumber;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getAddress() {
        return address;
    }
    public Long getId() {
        return id;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getWorkingHours() {
        return workingHours;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Set<Item> getItems() {
        return items;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
    public void setItems(Set<Item> items) {
        this.items = items;
    }
    
    public void addItem(Item item){
        this.items.add(item);
    }
}
