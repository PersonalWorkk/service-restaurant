package com.wedeliver.servicerestaurant.service;

import com.wedeliver.servicerestaurant.domain.Item;
import com.wedeliver.servicerestaurant.gateways.ItemDTO;
import com.wedeliver.servicerestaurant.payroll.ItemNotFoundException;
import com.wedeliver.servicerestaurant.payroll.RestaurantNotFoundException;
import com.wedeliver.servicerestaurant.repository.ItemRepository;
import com.wedeliver.servicerestaurant.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    public ItemService(){}
    public ItemService(ItemRepository itemRepository, RestaurantRepository restaurantRepository){
        this.itemRepository = itemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public void deleteItem(Long restaurantId, Long itemId){
        restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        // In case restaurant doesn`t exist show an explicit message instead of server error
        itemRepository.deleteById(itemId);
    }

    @Transactional
    public Item updateItemPrice(ItemDTO itemDTO, Long restaurantId, Long itemId){
        restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        // In case restaurant doesn`t exist show an explicit message instead of server error
        Item item = itemRepository.findById(itemId)
        .orElseThrow(() -> new ItemNotFoundException(itemId));
        item.setPrice(itemDTO.getPrice());
        return itemRepository.save(item);
    }

    @Transactional
    public Item updateItemName(ItemDTO itemDTO, Long restaurantId, Long itemId){
        restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        // In case restaurant doesn`t exist show an explicit message instead of server error
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new ItemNotFoundException(itemId));
        item.setName(itemDTO.getName());
        return itemRepository.save(item);
    }

}
