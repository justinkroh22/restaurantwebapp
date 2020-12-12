import { Component, OnInit } from '@angular/core';
import { Customer } from '../../Models/customer';
import { CustomerClass } from '../../customerclass';

import { CustomersService } from '../../Services/customers.service';

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




  constructor(private customersService: CustomersService) { }


  customerList: Customer[] = [];









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
