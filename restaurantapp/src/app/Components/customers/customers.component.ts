import { Component, OnInit } from '@angular/core';
import { Customer } from '../../Models/customer';
import { CustomerClass } from '../../customerclass';

import { CustomersService } from '../../Services/customers.service';
import { FormBuilder, FormGroup } from "@angular/forms";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {


  title = 'Customer List';

  customer: Customer = {
    id: 1,
    password: 'blah',
    firstName: 'Justin',
    lastName: 'Kroh',
    email: 'Justinkroh@gmail.com',
    address: 'test'

  };

  form: FormGroup;


  constructor(private formBuilder: FormBuilder, private customersService: CustomersService) {
    this.form = this.formBuilder.group({
      firstName: '',
      lastName: '',
      email: '',
      address: '',



    })

   }


  customerList: Customer[] = [];


  


  submitForm() {

    console.log(this.form.value);
    
    console.log(this.form.get('firstName')?.value);

    var firstName = this.form.get('firstName')?.value;
    var lastName = this.form.get('lastName')?.value;
    var email = this.form.get('email')?.value;
    var address = this.form.get('address')?.value;

    let customerclass: CustomerClass = new CustomerClass(firstName, lastName, email, address);

    console.log(customerclass);

    this.customersService.saveCustomerForm(customerclass)
    .subscribe((data => {console.log(data.body.customer_id)}));
  }



  getCustomers(): void {

    //this.customerList = this.customersService.getCustomers();

    this.customersService.getCustomers()
    .subscribe(customerList => this.customerList = customerList);

  }

  saveCustomer(customer: Customer): void {
    this.customersService.saveCustomer(customer)
    .subscribe(resp => console.log(resp));

  }

  ngOnInit(): void {
    this.getCustomers();
    this.saveCustomer(this.customer);
  }

}