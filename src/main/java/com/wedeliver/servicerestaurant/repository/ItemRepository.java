package com.wedeliver.servicerestaurant.repository;

import com.wedeliver.servicerestaurant.domain.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
