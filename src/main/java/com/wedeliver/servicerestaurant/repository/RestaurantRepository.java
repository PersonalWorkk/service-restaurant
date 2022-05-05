package com.wedeliver.servicerestaurant.repository;

import com.wedeliver.servicerestaurant.domain.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}
