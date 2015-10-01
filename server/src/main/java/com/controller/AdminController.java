package com.controller;

import com.models.Manager;
import com.models.Order;
import com.service.ManagerService;
import com.service.OrderService;
import com.service.TaxiSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kzub on 9/30/2015.
 */
@Controller
public class AdminController {

    @Autowired
    private ManagerService managerService;


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView managerPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("adminPage.html");
        return model;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = {"/admin/all"}, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Manager> managerFindAll() {
        return managerService.findAll();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin/", method = RequestMethod.POST)
    public void create(@RequestBody Manager manager) {
        managerService.save(manager);

    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Manager deleteManager(@PathVariable("id") long id) {
        Manager manager = managerService.findOne(id);
        managerService.delete(id);
        return manager;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    Manager updateManager(@PathVariable("id") long id, @RequestBody Manager manager) {
        return managerService.edit(manager);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Manager getManager(@PathVariable("id") long id) {
        return managerService.findOne(id);
    }
}
