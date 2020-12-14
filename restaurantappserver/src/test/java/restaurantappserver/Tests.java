package restaurantappserver;

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
        MvcResult result = mockMvc.perform(get("/customer/c/1")) // testing is done without the /api context of the DispatcherServlet f
                                                                        // from web.xml. The tests startup their own context not from the
        		.andDo(print())
               // .andExpect(status().isOk())
                .andReturn();
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json"));
        Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
    }
    
}