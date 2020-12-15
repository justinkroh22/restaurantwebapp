import { Injectable } from '@angular/core';
import { Customer } from '../Models/customer';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  private customersUrl = 'http://localhost:8081/restaurantappserver/api/customers';

  constructor(private http: HttpClient
    
    ) { }

  getCustomers(): Observable<Customer[]> {//rename to getAllCustomers?

    console.log(this.http.get<Customer[]>(this.customersUrl));
    return this.http.get<Customer[]>(this.customersUrl);
  }

  saveCustomer(customer: Customer): Observable<any> {
    return this.http.post<any>(this.customersUrl, customer, {
      observe: 'response'
    });
  }

//This is how it is done to grab respone data from posts
   httpOptions: any = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    observe: 'response'
  };

  saveCustomerForm(any: any): Observable<any> {
    return this.http.post<any>(this.customersUrl, any, this.httpOptions);
  }
}
