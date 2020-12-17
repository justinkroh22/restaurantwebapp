package com.restaurant.controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrdersController {
	
    @Autowired
    private OrdersDAO ordersDAO;
    
    @Autowired
    private MenuItemsDAO menuItemsDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Orders> getAllOrders() {
    	
    	/*
    	
        Orders o2 = new Orders();



        Set<MenuItems> itemsOrdered2 = new HashSet<>();

        itemsOrdered2.add(menuItemsDAO.getById(1));
        itemsOrdered2.add(menuItemsDAO.getById(2));
        itemsOrdered2.add(menuItemsDAO.getById(3));

        System.out.println(itemsOrdered2);

        o2.setOrderType("DELIVERY");
        o2.setStatus("PENDING");
        o2.setDeliveryAddress("11 LALA LANE");
        o2.setBillingAddress("11 LALA LANE");
        o2.setCustomer_id(2);
        o2.setItemsOrdered(itemsOrdered2);
        
        ordersDAO.save(o2);

    	
    	*/
    	System.out.println("Get all orders");
        return ordersDAO.getAll();
        
        
    }
    
    
    
    @GetMapping(path="o/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Orders getOrdersById(@PathVariable(name="orderId", required = true) Integer id) {
    	
    	System.out.println(id);
		return ordersDAO.getById(id);
	}
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addOrders(@RequestBody Orders o) throws URISyntaxException {
    
    	Set<MenuItems> actualItemsOrdered = new HashSet<>();
    	
    	
    	for (MenuItems m: o.getItemsOrdered() ) {
    		
    		actualItemsOrdered.add(menuItemsDAO.getById(m.getMenu_id()));
    		
    		
    	}
    	
    	o.getItemsOrdered().clear();
    	
    	System.out.println(o.getItemsOrdered());
    	
    	o.setItemsOrdered(actualItemsOrdered);
    	
    	System.out.println(o.getItemsOrdered());
    	
    	System.out.println(o);
    	ordersDAO.save(o);
    }
    
    
    
    
    

}