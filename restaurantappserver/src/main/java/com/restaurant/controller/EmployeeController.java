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




/**
 * The Employee Contoller "Handles" All incoming API requests. The handlers call the DAO which interacts with the database.
 *
 * @author Justin Kroh

 * */
@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeDAO;

    
    
    
    /**
     * Returns a list of all Employees
     *
     * @author Justin Kroh
     * 
     * @return List of employees in JSON format

     * */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAll();
    }
    
    
    
	/**
	 *	Gets an individual employee from the database by their ID
	 * @param employeeId
	 * @author Justin Kroh
	 * 
	 * @return Json representation of an individual customer
	 * */
    @GetMapping(path="e/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Employee getEmployeeById(@PathVariable(name="employeeId", required = true) Integer id) {

		return employeeDAO.getById(id);
	}
    
    
	/**
	 * Adds an employee to the database
	 * @param Employee Object, see model
	 * @author Justin Kroh
	 * 
	 * @return Returns the ID of the employee that was added  to the db
	 * */
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public Employee addEmployee(@RequestBody Employee e) throws URISyntaxException {
    
    	//employeeDAO.save(e);
    	return employeeDAO.save(e);
    	
    }
    
    
    /**
	 * Takes in a user name and password and checks again the database, 
	 * returns an Employee if credentials are accurate
	 * @param Login Object, see model- username and password JSON
	 * @author Justin Kroh
	 * 
	 * @return Returns employee object in JSON
	 * */
    @PostMapping(path="login", consumes=MediaType.APPLICATION_JSON_VALUE)
    public Employee checkCredentials(@RequestBody Login login) throws URISyntaxException {

    	
    	System.out.println(login);
    	System.out.println(login.getEmail());


    	
    	return employeeDAO.checkCredentials(login.getEmail(), login.getPassword());
    }
    

}