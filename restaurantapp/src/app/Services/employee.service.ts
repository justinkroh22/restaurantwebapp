/*import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor() { }
}*/

import { Injectable } from '@angular/core';
import { Employee } from './../Models/employee';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private employeesUrl = 'http://localhost:8081/api/employee';

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
}

