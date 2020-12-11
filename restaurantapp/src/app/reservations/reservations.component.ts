import { Component, OnInit } from '@angular/core';
import { Reservations } from '../reservations';
import { ReservationsService } from '../reservations.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  title = 'Reservation List';


  constructor(private reservationsService: ReservationsService) { }


  reservationsList: Reservations[] = [];

  getReservations(): void {

    this.reservationsService.getReservations()
    .subscribe(reservationsList => this.reservationsList = reservationsList);

    

  }

  ngOnInit(): void {
    this.getReservations();
  }

}
