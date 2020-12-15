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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
////import static MockMvcRequestBuilders.*;
//import static MockMvcResultMatchers.*;

import java.util.Arrays;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-application-context.xml"})
@WebAppConfiguration
public class Tests {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    CustomerController controller;

    MockMvc mockMvc;

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
        
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
       
    }
    
}