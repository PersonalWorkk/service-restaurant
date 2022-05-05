package com.wedeliver.servicerestaurant.controller;

import com.wedeliver.servicerestaurant.domain.Item;
import com.wedeliver.servicerestaurant.gateways.ItemDTO;
import com.wedeliver.servicerestaurant.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PutMapping("/api/restaurants/{restaurantId}/items/{itemId}")
    public Item updateItem(@RequestBody ItemDTO itemDTO, @PathVariable("restaurantId") Long restaurantId, @PathVariable("itemId") Long itemId){
        if (itemDTO.getPrice() != null){
            return itemService.updateItemPrice(itemDTO, restaurantId, itemId);
        }
        return itemService.updateItemName(itemDTO, restaurantId, itemId);
    }

    @DeleteMapping("/api/restaurants/{restaurantId}/items/{itemId}")
    public void deleteItem(@PathVariable("restaurantId") Long restaurantId, @PathVariable("itemId") Long itemId){
        itemService.deleteItem(restaurantId, itemId);
    }
}
