import { Component, OnInit } from '@angular/core';

import { OrderClass } from '../../Models/orderclass';

import { OrdersService } from '../../Services/orders.service';
import { FormBuilder, FormGroup } from "@angular/forms";
import { MenuItemClass } from 'src/app/Models/menuitemclass';
import { MenuItemsService } from 'src/app/Services/menuitems.service';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {

  title ='Orders Page';

  form: FormGroup;

  customerForm: FormGroup;


  constructor(private formBuilder: FormBuilder, private menuItemservice: MenuItemsService,
     private ordersService: OrdersService) {
    this.form = this.formBuilder.group({
      orderType: '',
      billingAddress: '',
      deliveryAddress: ''

    })

    this.customerForm = this.formBuilder.group({
      firstName: '',
      lastName: '',
      email: '',
      address: '',



    })

   }


  menuList: MenuItemClass[] = [];

  orderList: MenuItemClass[] = [];

  menuItem2?: MenuItemClass;

  getMenuItems(): void {

    this.menuItemservice.getMenuItems()
    .subscribe(menuList => this.menuList = menuList);

    console.log(this.menuList);

  }

  getMenuItem(id: string): void {

    this.menuItemservice.getMenuItem(id)
    .subscribe(menuItem2 => this.menuItem2 = menuItem2);

    console.log(this.menuItem2);

  }


  addItemToOrderList(menuID: any, itemName:any, description:any, price:any) {

    this.orderList.push(new MenuItemClass(itemName.value, description.value, price.value, menuID.value));
  }
  


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
    this.getMenuItems();

    this.getMenuItem('1');

  
  }

}
