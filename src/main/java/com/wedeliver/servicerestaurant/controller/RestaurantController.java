package com.wedeliver.servicerestaurant.controller;

import java.util.List;

import com.wedeliver.servicerestaurant.domain.Restaurant;
import com.wedeliver.servicerestaurant.gateways.RestaurantDTO;
import com.wedeliver.servicerestaurant.payroll.RestaurantNotFoundException;
import com.wedeliver.servicerestaurant.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    
    @GetMapping("/api/restaurants")
    public List<Restaurant> all(){
        return restaurantService.findAll();
    }

    @PostMapping("/api/restaurants")
    public ResponseEntity<String> createRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        // check if user is authorized
        // if yes
        restaurantService.saveRestaurant(restaurantDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
        // if no ...
    }

    @GetMapping("/api/restaurants/{id}")
    public Restaurant one(@PathVariable Long id){
        return restaurantService.findById(id);
    }

    // Update restaurant field by field
    @PutMapping("/api/restaurants/{id}")
    public Restaurant updateRestaurantName(@RequestBody RestaurantDTO restaurantDTO, @PathVariable Long id){
        if (restaurantDTO.getName() != null){
            return restaurantService.updateRestaurantName(restaurantDTO, id);
        } 
        if (restaurantDTO.getAddress() != null){
            return restaurantService.updateRestaurantAddress(restaurantDTO, id);
        }
        if (restaurantDTO.getCity() != null){
            return restaurantService.updateRestaurantCity(restaurantDTO, id);
        }
        if (restaurantDTO.getPhoneNumber() != null){
            return restaurantService.updateRestaurantPhoneNumber(restaurantDTO, id);
        }
        if (restaurantDTO.getPostalCode() != null){
            return restaurantService.updateRestaurantPostalCode(restaurantDTO, id);
        }
        return restaurantService.updateRestaurantWorkingHours(restaurantDTO, id);
    }

    @DeleteMapping("/api/restaurants/{id}")
    public void deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurant(id);
    }
    
}
