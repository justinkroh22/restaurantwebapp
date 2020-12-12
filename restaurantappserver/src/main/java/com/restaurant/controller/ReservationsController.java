package com.restaurant.controller;

import com.restaurant.data.ReservationsDAO;
import com.restaurant.models.Customer;
import com.restaurant.models.Reservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    @Autowired
    private ReservationsDAO reservationsDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservations> getAllReservations() {return reservationsDAO.getAll();}
    
    
    
    @GetMapping(path="r/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Reservations getCustomerById(@PathVariable(name="reservationId", required = true) Integer id) {

		return reservationsDAO.getById(id);
	}
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addCustomer(@RequestBody Reservations r) throws URISyntaxException {
    
    	reservationsDAO.save(r);
    }

    
    
}


