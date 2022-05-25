package com.wedeliver.servicerestaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wedeliver.servicerestaurant.gateways.RestaurantDTO;
import com.wedeliver.servicerestaurant.service.RestaurantService;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RestaurantController.class)
public class RestaurantControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private RabbitTemplate rabbitTemplate;
    

    @Test
    public void getAllRestaurants()throws Exception{
        mockMvc.perform(get("/api/restaurants")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void whenValidUrlAndMethodContentType_thenReturns200() throws Exception{
        RestaurantDTO restaurantDTO = new RestaurantDTO("Five Guys", "Eindhoven", "Frederiklaan 173", "5616NG", "09:00-20:00", "+310213891");

        mockMvc.perform(post("/api/restaurants")
            .content(objectMapper.writeValueAsString(restaurantDTO))
            .contentType("application/json"))
            .andExpect(status().isCreated());
    }
}
