package com.wedeliver.servicerestaurant.gateways;

import com.wedeliver.servicerestaurant.domain.Restaurant;

public class RestaurantDTO {
    private String name;
    private String city;
    private String address;
    private String postalCode;
    private String workingHours; // maybe use Time type
    private String phoneNumber;

    public RestaurantDTO(){}
    public RestaurantDTO(String name, String city, String address, String postalCode, String workingHours, String phoneNumber){
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
    public String getPostalCode() {
        return postalCode;
    }
    public String getWorkingHours() {
        return workingHours;
    }
    public String getPhoneNumber() {
        return phoneNumber;
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
    public Restaurant convert2Restaurant(){
        return new Restaurant(this.name, this.city, this.address, this.postalCode, this.workingHours, this.phoneNumber);
    }
}
