import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservations } from '../Models/reservations'

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {


  private justinsurl: string = 'http://localhost:8081/restaurantappserver/api/reservations';

  private reservationsUrl = this.justinsurl;

  constructor(private http: HttpClient) { }

  getReservations(): Observable<Reservations[]> {

    console.log(this.http.get<Reservations[]>(this.reservationsUrl));
    return this.http.get<Reservations[]>(this.reservationsUrl);
  }

  saveReservations(Reservations: Reservations): Observable<any>{
    return this.http.post<any>(this.reservationsUrl, Reservations, {
      observe: 'response'
    })
  }
}
