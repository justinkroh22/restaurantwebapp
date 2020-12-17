package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.data.CustomerDAO;
import com.restaurant.models.Customer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerDAO customerDAO;

    

    /*
    @RequestMapping(value = "d/{customerId}",  produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public @ResponseBody void deleteAuthorizationServer( @PathVariable("customerId") Integer id){
        System.out.printf("Testing");
        System.out.println(id);
        
        
    }
    */
    
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAllCustomers() {
    	System.out.println("Get all customers");
        return customerDAO.getAll();
    }
    
    
    @GetMapping(path="c/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Customer getCustomerById(@PathVariable(name="customerId", required = true) Integer id) {
    	
    	System.out.println(id);
		return customerDAO.getById(id);
	}
    
    
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public Customer addCustomer(@RequestBody Customer c) throws URISyntaxException {
    
    	System.out.println(c);
    	return customerDAO.save(c);
    }
    
 
    @DeleteMapping(path="delete")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@RequestParam int userId) throws URISyntaxException {
    	HttpHeaders httpHeaders = new HttpHeaders();
       
    	System.out.println(userId);
    	
    	customerDAO.deleteById(userId);
    	
    	
    	System.out.println("Return ResponseEntity");
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);

    }
    
    @DeleteMapping(path="d/{customerId}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@PathVariable(name="customerId", required = true) Integer id) throws URISyntaxException {
    	HttpHeaders httpHeaders = new HttpHeaders();
       
    	System.out.println(id);
    	
    	customerDAO.deleteById(id);
    	
    	System.out.println("Return ResponseEntity");
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);

    }
    
    

}