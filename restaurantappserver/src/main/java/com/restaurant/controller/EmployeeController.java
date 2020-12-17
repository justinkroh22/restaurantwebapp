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
import com.restaurant.models.Customer;
import com.restaurant.models.Employee;
import com.restaurant.models.Login;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() {
    	System.out.println("Get all employee");
        return employeeDAO.getAll();
    }
    
    @GetMapping(path="e/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Employee getEmployeeById(@PathVariable(name="employeeId", required = true) Integer id) {
    	
    	System.out.println(id);
		return employeeDAO.getById(id);
	}
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public Employee addEmployee(@RequestBody Employee e) throws URISyntaxException {
    
    	System.out.println(e);
    	return employeeDAO.save(e);
    	
    }
    
    
    @PostMapping(path="login", consumes=MediaType.APPLICATION_JSON_VALUE)
    public Employee checkCredentials(@RequestBody Login login) throws URISyntaxException {

    	
    	System.out.println(login);
    	System.out.println(login.getEmail());


    	
    	return employeeDAO.checkCredentials(login.getEmail(), login.getPassword());
    }
    

}