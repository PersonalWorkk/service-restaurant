package com.wedeliver.servicerestaurant.payroll;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(Long id){
        super("Could not find item " + id);
    }
}
