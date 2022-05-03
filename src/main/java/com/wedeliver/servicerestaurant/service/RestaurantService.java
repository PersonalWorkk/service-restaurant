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

    @Transactional
    public Restaurant updateRestaurant(RestaurantDTO restaurantDTO, Long id){
        return restaurantRepository.findById(id)
            .map(restaurant -> {
                restaurant.setAddress(restaurantDTO.getAddress());
                restaurant.setCity(restaurantDTO.getCity());
                restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
                restaurant.setPostalCode(restaurantDTO.getPostalCode());
                restaurant.setWorkingHours(restaurantDTO.getWorkingHours());
                return restaurantRepository.save(restaurant);
            })
            .orElseGet(() -> {
                Restaurant restaurant = restaurantDTO.convert2Restaurant();
                restaurant.setId(id);
                return restaurantRepository.save(restaurant);
            });
    }

    @Transactional
    public void deleteRestaurant(Long id){
        restaurantRepository.deleteById(id);
    }
}
