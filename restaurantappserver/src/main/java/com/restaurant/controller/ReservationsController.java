package com.restaurant.controller;

import com.restaurant.data.ReservationsDAO;
import com.restaurant.models.Customer;
import com.restaurant.models.Reservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    @Autowired
    private ReservationsDAO reservationsDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservations> getAllReservations() {
    	System.out.println("Returning all reservations");
    	return reservationsDAO.getAll();}
    
    
    
    @GetMapping(path="r/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Reservations getCustomerById(@PathVariable(name="reservationId", required = true) Integer id) {

    	System.out.println(id);
    	return reservationsDAO.getById(id); 
		
	}
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addCustomer(@RequestBody Reservations r) throws URISyntaxException {
    	
    	System.out.println(r);
    	reservationsDAO.save(r);
    }

    @RequestMapping(method=RequestMethod.PUT,path="/u/")
    @ResponseBody
    public void changeReservationStatus(@RequestParam(name="id") Integer id, @RequestParam(name="status") String status){
        System.out.println("Updated Reservation " + id + " Status to " + status);
        reservationsDAO.changeReservation(id,status);
    }

    
    
}


