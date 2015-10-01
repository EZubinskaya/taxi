package com.service;

import com.models.Order;
import com.models.User;

import java.util.List;

/**
 * Created by kzub on 9/24/2015.
 */
public interface OrderService {

    List<Order> findAll();

    Order save(Order order);

    void delete(long id);

    Order findById(long id);

    Order update(Order order);

    Order nearestTaxi(String phone, String userLocation, String car_class);

    double priceCalculate(long distance, int amount, double delta);
}
