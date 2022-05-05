package com.wedeliver.servicerestaurant.payroll;

import javax.persistence.EntityNotFoundException;

public class RestaurantNotFoundException extends EntityNotFoundException{
    public RestaurantNotFoundException(Long id){
        super("Could not find restaurant " + id);
    }
}
