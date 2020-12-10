package com.restaurant;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.restaurant.data.CustomerDAO;
import com.restaurant.models.Customer;
import com.restaurant.utils.DatabaseConnection;

@Component
@EnableWebMvc
public class Main {

	
	// DatabaseConnection databaseconnection = new DatabaseConnection();
	
	private CustomerDAO customerDAO;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//Main main = new Main();
		
		
		//main.testConnection();
		
		
        ApplicationContext ac = new ClassPathXmlApplicationContext("WEB-INF/application-context.xml");
        Main main = ac.getBean(Main.class);

        Customer c = new Customer();
        c.setEmail("test@email.com");
        c.setPassword("testpassword");
        c.setFirstName("test");
        c.setLastName("test");
        c.setAddress("test");


        main.customerDAO.save(c);

        System.out.println(main.customerDAO.getById(1));


        ((AbstractApplicationContext)ac).close();
		
		
		
		

	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	
	
	
	
	/*
	
	
    public void testConnection() {
    	
		SessionFactory factory = databaseconnection.getFactory();
		Session session = factory.openSession();
		session.close();
		factory.close();
    	
    	
    }
    
    */

}
