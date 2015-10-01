package com.controller;

import com.json.InfoAboutTaxi;
import com.json.UserOrder;
import com.models.Order;
import com.models.Status;
import com.models.Taxi;
import com.models.User;
import com.service.OrderService;
import com.service.TaxiSevice;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.sql.Time;
import java.util.Map;

/**
 * Created by kzub on 9/24/2015.
 */
@RestController
@RequestMapping("/")
public class UserController  {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private TaxiSevice taxiSevice;

    @RequestMapping(value = "userOrder", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.ALL_VALUE})
    public @ResponseBody Order saveOrder(@RequestBody UserOrder userOrder )   {
        return orderService.nearestTaxi(userOrder.getPhone(), userOrder.getLocation(), userOrder.getCarclass());
    }

}
