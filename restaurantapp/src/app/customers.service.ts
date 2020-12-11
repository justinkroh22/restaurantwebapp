import { Injectable } from '@angular/core';
import { Customer } from './customer';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  private customersUrl = 'api/customers';

  constructor(private http: HttpClient
    
    ) { }

  getCustomers(): Observable<Customer[]> {

    console.log(this.http.get<Customer[]>(this.customersUrl));
    return this.http.get<Customer[]>(this.customersUrl);
  }

  saveCustomer(customer: Customer): Observable<any> {
    return this.http.post<any>(this.customersUrl, customer, {
      observe: 'response'
    });
  }
}
