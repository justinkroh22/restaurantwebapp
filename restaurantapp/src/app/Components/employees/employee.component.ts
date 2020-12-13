import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeClass } from '../employeeclass';

import { EmployeeService } from '../employee.service';


@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  title = 'Employee List';

  employee: Employee ={
    //id:1,
    password: 'passwordtest',
    email: 'emailtest@cc.gmail',
    firstName: 'firstnametest',
    lastName: 'lastnametest',
    address: 'addresstest',
    user_type: 'usertypetest'
  };

  constructor(private employeeService:EmployeeService) { }

  employeeList: Employee[] = [];

  getEmployees(): void{
    this.employeeService.getEmployees()
    .subscribe(employeeList => this.employeeList = employeeList);

  }

  saveEmployee(employee: Employee): void{
    this.employeeService.saveEmployee(employee)
    .subscribe(rep => console.log(rep));
  }

  ngOnInit(): void {
    this.getEmployees();
    this.saveEmployee(this.employee);
  }

}
