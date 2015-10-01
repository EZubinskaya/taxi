package com.controller;

import com.models.Order;
import com.models.Status;
import com.service.OrderService;
import com.service.TaxiSevice;
import com.service.UserService;
import com.service.impl.OrderServiceImpl;
import com.util.GoogleFindDistanceAndDuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kzub on 9/23/2015.
 */
@Controller
public class ManagerController {

    @Autowired
    private OrderService orderService;


    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @RequestMapping(value = { "/manager" }, method = RequestMethod.GET)
    public ModelAndView managerPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("managerPage.html");
        return model;
    }

    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @RequestMapping(value = {"/manager/all" }, method = RequestMethod.GET)
    public @ResponseBody  List<Order> managerFindAll() {
       return orderService.findAll();
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/manager/", method = RequestMethod.POST)
    public void create(@RequestBody Order order) {
        Order orderNew = orderService.nearestTaxi(order.getPhone(), order.getUserLocation(), null);
        orderService.save(orderNew);

    }

    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @RequestMapping(value = "/manager/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Order deleteOrder(@PathVariable("id") long id) {
        Order order = orderService.findById(id);
        orderService.delete(id);
        return order;
    }

    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @RequestMapping(value = "/manager/{id}", method = RequestMethod.PUT)
    public @ResponseBody Order updateOrder(@PathVariable("id") long id, @RequestBody Order orderUpdate) {
        return orderService.update(orderUpdate);
    }

    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @RequestMapping(value = "/manager/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order getOrder(@PathVariable("id") long id) {
        return orderService.findById(id);
    }


}
