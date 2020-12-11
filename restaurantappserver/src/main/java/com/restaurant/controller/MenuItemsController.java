package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.data.CustomerDAO;
import com.restaurant.data.EmployeeDAO;
import com.restaurant.data.MenuItemsDAO;
import com.restaurant.models.Customer;
import com.restaurant.models.Employee;
import com.restaurant.models.MenuItems;

import java.util.List;

@RestController
@RequestMapping("/menuitems")
public class MenuItemsController {
    @Autowired
    private MenuItemsDAO menuItemsDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuItems> getAllMenuItems() {
        return menuItemsDAO.getAll();
    }

}