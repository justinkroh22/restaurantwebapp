import { Component, OnInit } from '@angular/core';
import { Employee } from '../../Models/employee';
import { EmployeeClass } from '../../Models/employeeclass';


import { EmployeeService } from '../../Services/employee.service';
import { FormBuilder, FormGroup } from "@angular/forms";



@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  title = 'Employee List';

  employee: Employee ={
    id:1,
    password: 'passwordtest',
    email: 'emailtest@cc.gmail',
    firstName: 'firstnametest',
    lastName: 'lastnametest',
    address: 'addresstest',
    user_type: 'usertypetest'
  };

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,private employeeService:EmployeeService) {
    this.form = this.formBuilder.group({
      firstName: '',
      lastName: '',
      email: '',
      address: '',
    })
   }

  employeeList: Employee[] = [];

  submitForm() {

    console.log(this.form.value);
    
    console.log(this.form.get('firstName')?.value);

    var firstName = this.form.get('firstName')?.value;
    var lastName = this.form.get('lastName')?.value;
    var email = this.form.get('email')?.value;
    var address = this.form.get('address')?.value;

    let employeeclass: EmployeeClass = new EmployeeClass(firstName, lastName, email, address);

    console.log(employeeclass);

    this.employeeService.saveEmployeeForm(employeeclass)
    .subscribe((data => {console.log(data.body.customer_id)}));
  }


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
