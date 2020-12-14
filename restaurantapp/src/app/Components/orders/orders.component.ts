import { Component, OnInit } from '@angular/core';

import { OrderClass } from '../../Models/orderclass';

import { OrdersService } from '../../Services/orders.service';
import { FormBuilder, FormGroup } from "@angular/forms";
import { MenuItemClass } from 'src/app/Models/menuitemclass';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  title ='Orders Page';

  form: FormGroup;


  constructor(private formBuilder: FormBuilder, private ordersService: OrdersService) {
    this.form = this.formBuilder.group({
      orderType: '',
      billingAddress: '',
      deliveryAddress: ''

    })

   }


  //customerList: Customer[] = [];


  


  submitForm() {

    console.log(this.form.value);
    
    console.log(this.form.get('orderType')?.value);

    var orderType = this.form.get('orderType')?.value;
    var deliveryAddress = this.form.get('deliveryAddress')?.value;
    var billingAddress = this.form.get('billingAddress')?.value;

    var status: string = 'pending';
    var customer_id: number = 3;

    let menuItemObject1: MenuItemClass = new MenuItemClass('pizza', 'pepporonia pizza', 23.34);
    let menuItemObject2: MenuItemClass = new MenuItemClass('calzone', 'peperoni', 12.34);


    var itemsOrdered: MenuItemClass[] = [menuItemObject1, menuItemObject2];

    let orderObject: OrderClass = new OrderClass(orderType, status, customer_id,
       deliveryAddress, billingAddress, itemsOrdered);

    console.log(orderObject);



    // This data is returned data after post just use .whatever field
    this.ordersService.saveOrderForm(orderObject)
    .subscribe((data => {console.log(data.body)}));
  }



  ngOnInit(): void {
  }

}
