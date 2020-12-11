import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservations } from './reservations';

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {

  private reservationsUrl = 'api/reservations';

  constructor(private http: HttpClient
    
    ) { }

  getReservations(): Observable<Reservations[]> {

    console.log(this.http.get<Reservations[]>(this.reservationsUrl));
    return this.http.get<Reservations[]>(this.reservationsUrl);
  }}
