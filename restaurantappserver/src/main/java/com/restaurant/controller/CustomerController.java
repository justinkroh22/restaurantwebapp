package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }
    
    
    @GetMapping(path="c/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Customer getCustomerById(@PathVariable(name="customerId", required = true) Integer id) {

		return customerDAO.getById(id);
	}
    
    
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public Customer addCustomer(@RequestBody Customer c) throws URISyntaxException {
    
    	return customerDAO.save(c);
    }

}