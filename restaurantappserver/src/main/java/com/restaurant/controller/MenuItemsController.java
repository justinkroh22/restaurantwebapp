package com.restaurant.controller;

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
import com.restaurant.models.Customer;
import com.restaurant.models.Employee;
import com.restaurant.models.MenuItems;

import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/menuitems")
public class MenuItemsController {
    @Autowired
    private MenuItemsDAO menuItemsDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuItems> getAllMenuItems() {
        return menuItemsDAO.getAll();
    }
    
    
    @GetMapping(path="m/menuId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public MenuItems getMenuItemById(@PathVariable(name="menuId", required = true) Integer id) {

		return menuItemsDAO.getById(id);
	}
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addEmployee(@RequestBody MenuItems m) throws URISyntaxException {
    
    	menuItemsDAO.save(m);
    }

}