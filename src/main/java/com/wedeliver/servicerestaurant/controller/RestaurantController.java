package com.wedeliver.servicerestaurant.controller;

import java.util.List;
import java.util.Set;

import com.wedeliver.servicerestaurant.domain.Item;
import com.wedeliver.servicerestaurant.domain.Restaurant;
import com.wedeliver.servicerestaurant.gateways.ItemDTO;
import com.wedeliver.servicerestaurant.gateways.OrderDTO;
import com.wedeliver.servicerestaurant.gateways.RestaurantDTO;
import com.wedeliver.servicerestaurant.rabbitmq.config.MessagingConfig;
import com.wedeliver.servicerestaurant.service.RestaurantService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    
    @Autowired
    private RabbitTemplate template;

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
    public ResponseEntity<Restaurant> one(@PathVariable Long id){
        return new ResponseEntity<Restaurant>(restaurantService.findById(id), HttpStatus.FOUND);
    }

    // Update restaurant field by field
    @PutMapping("/api/restaurants/{id}")
    public Restaurant updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable Long id){
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

    @PutMapping("/api/restaurants/{id}/items")
    public void updateRestaurantItems(@RequestBody ItemDTO itemDTO, @PathVariable Long id){
        restaurantService.updateRestaurantItem(itemDTO, id);
    }

    @GetMapping("/api/restaurants/{id}/items")
    public Set<Item> allItems(@PathVariable Long id){
        return restaurantService.getAllItemsByRestaurantId(id);
    }
    
    @PostMapping("/api/restaurant/{id}/orders")
    public ResponseEntity<String> createOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        // calculate total order price
        orderDTO.calculateTotalPrice();
        // get the restaurant by id and set it for the order
        orderDTO.setRestaurant(restaurantService.findById(id));
        // send the dto object through the message bus to the order microservice
        // Is it a good idea to store all created order in the same database in the order service?
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderDTO);        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
