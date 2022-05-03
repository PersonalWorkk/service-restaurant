package com.wedeliver.servicerestaurant.payroll;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(Long id){
        super("Could not find restaurant " + id);
    }
}
