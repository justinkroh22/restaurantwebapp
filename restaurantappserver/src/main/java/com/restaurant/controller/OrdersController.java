package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.data.CustomerDAO;
import com.restaurant.data.EmployeeDAO;
import com.restaurant.data.MenuItemsDAO;
import com.restaurant.data.OrdersDAO;
import com.restaurant.models.Customer;
import com.restaurant.models.Employee;
import com.restaurant.models.MenuItems;
import com.restaurant.models.Orders;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersDAO ordersDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Orders> getAllStarships() {
        return ordersDAO.getAll();
    }

}