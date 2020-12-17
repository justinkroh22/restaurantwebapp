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


/**
 * The MenuItems Contoller "Handles" All incoming API requests. The handlers call the DAO which interacts with the database.
 *
 * @author Justin Kroh

 * */
@CrossOrigin
@RestController
@RequestMapping("/menuitems")
public class MenuItemsController {
    @Autowired
    private MenuItemsDAO menuItemsDAO;

    
    /**
     * Returns a list of all menu items
     *
     * @author Justin Kroh
     * 
     * @return List of menuItems in JSON format

     * */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuItems> getAllMenuItems() {
    	System.out.println("Det all menu items");
        return menuItemsDAO.getAll();
    }
    
    
    
	/**
	 * Gets an individual menu item from the database by their ID
	 * @param employeeId
	 * @author Justin Kroh
	 * 
	 * @return Json representation of an individual menuItems
	 * */
    @GetMapping(path="m/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public MenuItems getMenuItemById(@PathVariable(name="menuId", required = true) Integer id) {
    	
    	System.out.println(id);
		return menuItemsDAO.getById(id);
	}
    
    
    /**
	 * Adds a menuItem to the database
	 * @param MenuItem Object, see model
	 * @author Justin Kroh
	 * 
	 * @return Returns the ID of the menuITem that was added  to the db
	 * */
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addMenuItem(@RequestBody MenuItems m) throws URISyntaxException {
    	
    	System.out.println(m);
    	menuItemsDAO.save(m);
    }

    
    /**
	 * Deletes a MenuItem from the database
	 * @param Menu_Id path
	 * @author Ronald Martz
	 * 
	 * @return Returns Http Status Okay
	 * */
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