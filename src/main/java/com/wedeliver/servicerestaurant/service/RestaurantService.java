package com.wedeliver.servicerestaurant.service;

import java.util.List;

import com.wedeliver.servicerestaurant.domain.Restaurant;
import com.wedeliver.servicerestaurant.gateways.RestaurantDTO;
import com.wedeliver.servicerestaurant.payroll.RestaurantNotFoundException;
import com.wedeliver.servicerestaurant.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantService(){}
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public Restaurant saveRestaurant(RestaurantDTO restaurantDTO){
        Restaurant restaurant = restaurantDTO.convert2Restaurant();
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Transactional
    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }

    @Transactional
    public Restaurant findById(Long id){
        return restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    // Update restaurant field by field
    @Transactional
    public Restaurant updateRestaurantName(RestaurantDTO restaurantDTO, Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setName(restaurantDTO.getName());
        return restaurantRepository.save(restaurant);
    }
    @Transactional
    public Restaurant updateRestaurantCity(RestaurantDTO restaurantDTO, Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setCity(restaurantDTO.getCity());
        return restaurantRepository.save(restaurant);
    }
    @Transactional
    public Restaurant updateRestaurantAddress(RestaurantDTO restaurantDTO, Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setAddress(restaurantDTO.getAddress());
        return restaurantRepository.save(restaurant);
    }
    @Transactional
    public Restaurant updateRestaurantPostalCode(RestaurantDTO restaurantDTO, Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setPostalCode(restaurantDTO.getPostalCode());
        return restaurantRepository.save(restaurant);
    }
    @Transactional
    public Restaurant updateRestaurantWorkingHours(RestaurantDTO restaurantDTO, Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setWorkingHours(restaurantDTO.getWorkingHours());
        return restaurantRepository.save(restaurant);
    }
    @Transactional
    public Restaurant updateRestaurantPhoneNumber(RestaurantDTO restaurantDTO, Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void deleteRestaurant(Long id){
        restaurantRepository.deleteById(id);
    }
}
