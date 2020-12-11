import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CustomerClass } from '../customerclass';

import { CustomersService } from '../customers.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {


  title = 'Customer List';

  /*
  customer: Customer = {
    user_id: 1,
    firstName: 'Justin',
    lastName: 'Kroh',
    email: 'Justinkroh@gmail.com'

  };

*/


  constructor(private customersService: CustomersService) { }


  customerList: Customer[] = [];

  email: string;
  firstName: string = '';
  lastName: string = '';


  customer: CustomerClass = new CustomerClass();




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
  }

}
