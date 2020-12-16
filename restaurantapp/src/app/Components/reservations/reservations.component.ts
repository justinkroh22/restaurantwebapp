import { Component, OnInit } from '@angular/core';
import { Reservations } from '../../Models/reservations';
import { ReservationsService } from '../../Services/reservations.service';
import { CustomersService } from '../../Services/customers.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit{

  title = 'Reservation List';

  public employee = false;


  constructor(private reservationsService: ReservationsService, private customersService: CustomersService, private http: HttpClient, private authService: AuthService) {
    this.customersService = customersService;
  }



  reservationsList: Reservations[] = [];

  getReservations(): void {

    this.reservationsService.getReservations()
    .subscribe(reservationsList => this.reservationsList = reservationsList);

  }

  ngOnInit(): void {
    this.getReservations();
    this.checkUserType();
  }

  checkUserType(): void{
    if(this.authService.isLoggedIn){
      this.employee = true;
    }
  }

  saveReservations(reservationDate: string, reservationTime: string): void {

    let reservation: Reservations = {
      reservation_id : -1,//Set as -1 due to Hibernate/SQL generating the ID
      customer_id: 1,//Expand further to grab customer id
      date : reservationDate,
      time : reservationTime,
      status : 'Booked'
    };

    this.reservationsService.saveReservations(reservation)
      .subscribe(resp => { console.log(resp);});
  }

  cancelReservations(id:any): void{
    let resID:number = parseInt(id);
    let status:string = 'Cancelled';

    this.reservationsService.cancelReservation(resID,status)
      .subscribe(resp => {console.log(resp);});
  }

}
