import { Component, OnInit } from '@angular/core';
import { Reservations } from '../../Models/reservations';
import { ReservationsService } from '../../Services/reservations.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit{

  title = 'Reservation List';


  constructor(private reservationsService: ReservationsService) { }



  reservationsList: Reservations[] = [];

  getReservations(): void {

    this.reservationsService.getReservations()
    .subscribe(reservationsList => this.reservationsList = reservationsList);

    

  }

  ngOnInit(): void {
    // this.getReservations();
  }

  saveReservations(reservationDate: string, reservationTime: string): void {

    let reservation: Reservations = {
      reservation_id : 42,
      customer_id: 1,
      date : reservationDate,
      time : reservationTime
    };

    console.log(reservation);

    this.reservationsService.saveReservations(reservation)
      .subscribe(resp => console.log(resp));
  }

}
