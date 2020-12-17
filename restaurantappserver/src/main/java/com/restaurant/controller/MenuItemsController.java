package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    	System.out.println("Det all menu items");
        return menuItemsDAO.getAll();
    }
    
    
    @GetMapping(path="m/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public MenuItems getMenuItemById(@PathVariable(name="menuId", required = true) Integer id) {
    	
    	System.out.println(id);
		return menuItemsDAO.getById(id);
	}
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addMenuItem(@RequestBody MenuItems m) throws URISyntaxException {
    	
    	System.out.println(m);
    	menuItemsDAO.save(m);
    }

    @DeleteMapping(path="r/{menu_id}")
    @ResponseBody
    public ResponseEntity<String> deleteFromMenu(@PathVariable(name="menu_id", required = true) Integer id) throws URISyntaxException{
    	System.out.println("Create httpHeaders");
        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println("Reaching the controller");
        System.out.println(id);
        menuItemsDAO.remove(id);
        System.out.println("Return ResponseEntity");
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }
    
    

}