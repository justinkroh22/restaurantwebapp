
import { Injectable } from '@angular/core';
import { Employee } from './employee';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private employeesUrl = 'http://localhost:8081/restaurantappserver/api/employee';

  constructor(private http: HttpClient
    
    ) { }

  getEmployees(): Observable<Employee[]> {

    console.log(this.http.get<Employee[]>(this.employeesUrl));
    return this.http.get<Employee[]>(this.employeesUrl);
  }

  saveEmployee(employee: Employee): Observable<any> {
    return this.http.post<any>(this.employeesUrl, employee, {
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

  saveEmployeeForm(any: any): Observable<any> {
    return this.http.post<any>(this.employeesUrl, any, this.httpOptions);
  }

}

