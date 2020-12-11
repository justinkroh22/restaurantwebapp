package com.restaurant.controller;

import com.restaurant.data.ReservationsDAO;
import com.restaurant.models.Reservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    @Autowired
    private ReservationsDAO reservationsDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservations> getAllReservations() {return reservationsDAO.getAll();}
}
