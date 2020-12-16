package com.restaurant.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.restaurant.controller.CustomerController;
import com.restaurant.controller.EmployeeController;
import com.restaurant.controller.MenuItemsController;
import com.restaurant.controller.OrdersController;
import com.restaurant.controller.ReservationsController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



////import static MockMvcRequestBuilders.*;
//import static MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.json.Json;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-application-context.xml"})
@WebAppConfiguration
public class Tests {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    CustomerController controller;
    
    MockMvc mockMvc;
    
//*****************Testing for CustomerController********************************    

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    
    
 
    
    
    @Test
    public void getCustomerController_ThenReturnCustomer() throws Exception {
        MvcResult result = mockMvc.perform(get("/customers")) // testing is done without the /api context of the DispatcherServlet f
        

        		// from web.xml. The tests startup their own context not from the
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();
        
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		
		System.out.println(result.getResponse().getContentAsString());
        
		
		   String json = Json.createObjectBuilder()
	                .add("key1", "value1")
	                .add("key2", "value2")
	                .build()
	                .toString();
		   
		   System.out.println(json);
		
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
       
     
        
        
    }
    
    @Test
    public void getCustomerController_ThenReturnSpecificCustomer() throws Exception {
        MvcResult result = mockMvc.perform(get("/customers/c/1")) // testing is done without the /api context of the DispatcherServlet f
        

        		// from web.xml. The tests startup their own context not from the
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();
        
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		

		String databaseResponse = result.getResponse().getContentAsString().toString(); 
		System.out.println("*************************************");

	    
		String json = Json.createObjectBuilder()
	            .add("customer_id", 1)
	            .add("password", "12345")
	            .add("email", "test")
	            .add("firstName", "test")
	            .add("lastName", "test")
	            .add("address", "test")
	            .build()
	            .toString();
		System.out.println(json);

		
		String actual = databaseResponse;
		
		String expected = json;
        
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
       
    }
    
  //*****************Testing for CustomerController for Post method********************************    


//    @Test
//    public void getCustomerController_ThenAddCustomer() throws Exception {
//        MvcResult result = mockMvc.perform(post("/customers")) // testing is done without the /api context of the DispatcherServlet f
//        
//
//        		// from web.xml. The tests startup their own context not from the
//        		.andDo(print())
//        		.andExpect(status().isOk())
//                .andReturn();
//        
//		System.out.println(result.getResponse().getHeader("Content-Type"));
//		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
//		
//		System.out.println(result.getResponse().getStatus());
//		
//		int actual = result.getResponse().getStatus(); 
//		
//		int expected = 200;
//        
//		Assert.assertEquals(actual, expected);
//        
//		
//		
//		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
//        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
//       
//    }
//    
    
//*****************Testing for EmployeeController********************************
 
    @Autowired
    EmployeeController controllerEmp;

  
    @Before
    public void setupEmp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getEmployeeController_ThenReturnEmployee() throws Exception {
        MvcResult result = mockMvc.perform(get("/employee")) // testing is done without the /api context of the DispatcherServlet f
        

        		// from web.xml. The tests startup their own context not from the
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();
        
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		
		System.out.println(result.getResponse().getContentAsString());
        
		
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
       
    }
    
    @Test
    public void getEmployeeController_ThenReturnSpecificEmployee() throws Exception {
        MvcResult result = mockMvc.perform(get("/employee/e/4")) // testing is done without the /api context of the DispatcherServlet f
        

        		// from web.xml. The tests startup their own context not from the
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();
        
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		
		String x = result.getResponse().getContentAsString();
		
		String slice = x.substring(1);
		
		String sliced_again = slice.substring(0, slice.length() - 1);
		
		String[] split = sliced_again.split(",");
		
		System.out.println(slice);
		System.out.println(sliced_again);
		System.out.println(Arrays.toString(split));
		

		String testItem = split[1];	 
		System.out.println(testItem);
		
		String[] passwordarray = testItem.split(":");
		System.out.println(Arrays.toString(passwordarray));
		System.out.println(passwordarray[1]);
		
		String slicedPassword = passwordarray[1].substring(1, passwordarray[1].length() -1);
		
		int password = Integer.parseInt(slicedPassword);
		
		System.out.println(password);
		
		int actual = password;
		

		int expected = 12345;		
		
		String x1 = result.getResponse().getContentAsString();

		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
       
    }
    
    
//*****************Testing for MenuItemsController********************************
    
  @Autowired
  MenuItemsController controllerMenuItem;

  @Before
  public void setupMenuItem() {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void getMenuItemsController_ThenReturnMenuItems() throws Exception {
      MvcResult result = mockMvc.perform(get("/menuitems")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		
		System.out.println(result.getResponse().getContentAsString());
      
		
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
      Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
  @Test
  public void getMenuItemsController_ThenReturnSpecificMenuItems() throws Exception {
      MvcResult result = mockMvc.perform(get("/menuitems/m/4")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		
		String x = result.getResponse().getContentAsString();
		
		String slice = x.substring(1);
		
		String sliced_again = slice.substring(0, slice.length() - 1);
		
		String[] split = sliced_again.split(",");
		
		System.out.println(slice);
		System.out.println(sliced_again); 
		System.out.println(Arrays.toString(split));
		
		String testItem = split[1];	 
		System.out.println(testItem);
		
		String[] passwordarray = testItem.split(":");
		
		System.out.println(Arrays.toString(passwordarray));
		System.out.println(passwordarray[1]);
		
		String slicedPassword = passwordarray[1].substring(1, passwordarray[1].length() -1);
		
		String actual = slicedPassword;	
		
		String expected = "burger";
      
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
      Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
//*****************Testing for OrdersController********************************
  
  @Autowired
  OrdersController controllerOrder;

  @Before
  public void setupOrder() {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void getOrdersController_ThenReturnOrder() throws Exception {
      MvcResult result = mockMvc.perform(get("/menuitems")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		
		System.out.println(result.getResponse().getContentAsString());
      
		
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
      Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
  @Test
  public void getOrdersController_ThenReturnSpecificOrder() throws Exception {
      MvcResult result = mockMvc.perform(get("/orders/o/1")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		
		String x = result.getResponse().getContentAsString();
		
		String slice = x.substring(1);
		
		String sliced_again = slice.substring(0, slice.length() - 1);
		
		String[] split = sliced_again.split(",");
		
		System.out.println(slice);
		System.out.println(sliced_again); 
		System.out.println(Arrays.toString(split));
		
		String testItem = split[1];	 
		System.out.println(testItem);
		
		String[] passwordarray = testItem.split(":");
		
		System.out.println(Arrays.toString(passwordarray));
		System.out.println(passwordarray[1]);
		
		String slicedPassword = passwordarray[1].substring(1, passwordarray[1].length() -1);
		
		String actual = slicedPassword;	
	
		String expected = "DELIVERY";
    
      Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
      Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
//*****************Testing for ReservationsController********************************
  
  @Autowired
  ReservationsController controllerReservation;

  @Before
  public void setupResrv() {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void getReservationsController_ThenReturnReservation() throws Exception {
      MvcResult result = mockMvc.perform(get("/reservations")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		
		System.out.println(result.getResponse().getContentAsString());
      
		
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
      Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
  @Test
  public void getReservationsController_ThenReturnSpecificReservation() throws Exception {
      MvcResult result = mockMvc.perform(get("/reservations/r/1")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		
		String x = result.getResponse().getContentAsString();
		
		String slice = x.substring(1);
		
		String sliced_again = slice.substring(0, slice.length() - 1);
		
		String[] split = sliced_again.split(",");
		
		System.out.println(slice);
		System.out.println(sliced_again); 
		System.out.println(Arrays.toString(split));
		
		String testItem = split[1];	 
		System.out.println(testItem);
		
		String[] passwordarray = testItem.split(":");
		
		System.out.println(Arrays.toString(passwordarray));
		System.out.println(passwordarray[1]);
				
		int password = Integer.parseInt(passwordarray[1]);
		int actual = password; 
		
		int expected = 1; 
		
	
      Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
      Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
}