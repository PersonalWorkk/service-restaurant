package com.wedeliver.servicerestaurant.service;

import java.util.List;
import java.util.Set;

import com.wedeliver.servicerestaurant.domain.Item;
import com.wedeliver.servicerestaurant.domain.Restaurant;
import com.wedeliver.servicerestaurant.gateways.ItemDTO;
import com.wedeliver.servicerestaurant.gateways.RestaurantDTO;
import com.wedeliver.servicerestaurant.payroll.RestaurantNotFoundException;
import com.wedeliver.servicerestaurant.repository.ItemRepository;
import com.wedeliver.servicerestaurant.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ItemRepository itemRepository;
    public RestaurantService(){}
    public RestaurantService(RestaurantRepository restaurantRepository, ItemRepository itemRepository){
        this.restaurantRepository = restaurantRepository;
        this.itemRepository = itemRepository;
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

    @Transactional
    public void updateRestaurantItem(ItemDTO itemDTO, Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));
        // Create the item database entry
        Item item = itemDTO.convert2Item(restaurant);
        itemRepository.save(item);
        restaurant.addItem(item);
    }

    @Transactional
    public Set<Item> getAllItemsByRestaurantId(Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));
        return restaurant.getItems();
    }
}
