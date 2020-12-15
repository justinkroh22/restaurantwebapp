import { Component, OnInit } from '@angular/core';

import { OrderClass } from '../../Models/orderclass';
import { CustomerClass } from '../../customerclass';

import { OrdersService } from '../../Services/orders.service';
import { CustomersService } from '../../Services/customers.service';

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

  orderForm: FormGroup;
  customerForm: FormGroup;

  orderForm2: FormGroup;


  constructor(private formBuilder: FormBuilder, private menuItemservice: MenuItemsService,
     private ordersService: OrdersService, private customersService: CustomersService) {
    this.orderForm = this.formBuilder.group({
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

    this.orderForm2 = this.formBuilder.group({
      firstName: '',
      lastName: '',
      email: '',
      address: '',
      orderType: '',
      billingAddress: '',
      deliveryAddress: ''

    })

   }


  customer_id?: any; 
 
  m?: any = 'no orders';

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


  addItemToOrderList(menuID: any) {

    console.log(menuID.innerHTML);

   
    this.menuItemservice.getMenuItem(menuID.innerHTML)
    .subscribe(m => this.m = m);

    //if (this.m != null){
    this.orderList.push(this.m);
    // }

    //this.orderList.push(new MenuItemClass(this.m.itemName, m.description, m.price, m.menu_id);

    //this.orderList.push(new MenuItemClass(itemName.value, description.value, price.value, menuID.value));
  }

  addItemToOrderList2(menuID: any, itemName: any, description: any, price:any) {

    console.log(menuID.innerHTML);
    console.log(itemName.innerHTML);
    console.log(description.innerHTML);
    console.log(price.innerHTML);

   
    //this.menuItemservice.getMenuItem(menuID.innerHTML)
    //.subscribe(m => this.m = m);

    //if (this.m != null){
    //this.orderList.push(this.m);
    // }

    this.orderList.push(new MenuItemClass(itemName.innerHTML, description.innerHTML, price.innerHTML, menuID.innerHTML));

    //this.orderList.push(new MenuItemClass(itemName.value, description.value, price.value, menuID.value));
  }
  

  submitCustomerForm() {

    console.log(this.customerForm.value);
    
    console.log(this.customerForm.get('firstName')?.value);

    var firstName = this.customerForm.get('firstName')?.value;
    var lastName = this.customerForm.get('lastName')?.value;
    var email = this.customerForm.get('email')?.value;
    var address = this.customerForm.get('address')?.value;

    let customerclass: CustomerClass = new CustomerClass(firstName, lastName, email, address);

    console.log(customerclass);

    // This data is returned data after post just use .whatever field
    this.customersService.saveCustomerForm(customerclass)
    .subscribe((customer_id => this.customer_id = customer_id.body.customer_id));
    
   // .subscribe((data => {console.log(data.body.customer_id)}))

  }

  submitOrderForm() {

    console.log(this.orderForm.value);
    
    console.log(this.orderForm.get('orderType')?.value);

    var orderType = this.orderForm.get('orderType')?.value;
    var deliveryAddress = this.orderForm.get('deliveryAddress')?.value;
    var billingAddress = this.orderForm.get('billingAddress')?.value;

    var status: string = 'pending';
    var customer_id: number = 3;

    let menuItemObject1: MenuItemClass = new MenuItemClass('pizza', 'pepporonia pizza', 23.34, 5);
    let menuItemObject2: MenuItemClass = new MenuItemClass('calzone', 'peperoni', 12.34, 2);


    var itemsOrdered: MenuItemClass[] = [menuItemObject1, menuItemObject2];
    

    let orderObject: OrderClass = new OrderClass(orderType, status, customer_id,
       deliveryAddress, billingAddress, itemsOrdered);


      // This data is returned data after post just use .whatever field
    this.ordersService.saveOrderForm(orderObject)
    .subscribe((data => {console.log(data.body)}));

    console.log(orderObject);

    

  }



  submitOrderData(c_id: number) {

    var orderType = this.orderForm2.get('orderType')?.value;
    var deliveryAddress = this.orderForm2.get('deliveryAddress')?.value;
    var billingAddress = this.orderForm2.get('billingAddress')?.value;

    var status: string = 'pending';
    //var customer_id: number = 3;    
    //let menuItemObject1: MenuItemClass = new MenuItemClass('pizza', 'pepporonia pizza', 23.34);
    //let menuItemObject2: MenuItemClass = new MenuItemClass('calzone', 'peperoni', 12.34);
    //var itemsOrdered: MenuItemClass[] = [menuItemObject1, menuItemObject2];

    if (c_id != null) {

    let orderObject: OrderClass = new OrderClass(orderType, status, c_id,
       deliveryAddress, billingAddress, this.orderList);


      // This data is returned data after post just use .whatever field
    this.ordersService.saveOrderForm(orderObject)
    .subscribe((data => {console.log(data.body)}));

    console.log(orderObject);

    }  
    else console.log("issue with ID");


  }


  submitOrderForm2() {

    console.log(this.orderForm2.value);
    
    console.log(this.orderForm2.get('orderType')?.value);

    var c_id: any;


    var firstName = this.orderForm2.get('firstName')?.value;
    var lastName = this.orderForm2.get('lastName')?.value;
    var email = this.orderForm2.get('email')?.value;
    var address = this.orderForm2.get('address')?.value;

    let customerclass: CustomerClass = new CustomerClass(firstName, lastName, email, address);

    console.log(customerclass);




    // This Posts and Returns the Customer ID
    this.customersService.saveCustomerForm(customerclass)
    .subscribe(customer_id => {this.customer_id = customer_id.body.customer_id}, error => {console.log(error)}, () => this.submitOrderData(this.customer_id));


    // .subscribe(customer_id => {this.customer_id = customer_id.body.customer_id}, error => {console.log(error)}, () => this.submitOrderData(this.customer_id));

    //(data => {console.log(data.body)})

    //this.customersService.saveCustomerForm(customerclass)
    //.subscribe((customer_id => c_id = customer_id.body.customer_id));
/*
    console.log(c_id);

    console.log(this.customer_id);

    var orderType = this.orderForm2.get('orderType')?.value;
    var deliveryAddress = this.orderForm2.get('deliveryAddress')?.value;
    var billingAddress = this.orderForm2.get('billingAddress')?.value;

    var status: string = 'pending';
    //var customer_id: number = 3;    
    //let menuItemObject1: MenuItemClass = new MenuItemClass('pizza', 'pepporonia pizza', 23.34);
    //let menuItemObject2: MenuItemClass = new MenuItemClass('calzone', 'peperoni', 12.34);
    //var itemsOrdered: MenuItemClass[] = [menuItemObject1, menuItemObject2];

    if (this.customer_id != null) {

    let orderObject: OrderClass = new OrderClass(orderType, status, this.customer_id,
       deliveryAddress, billingAddress, this.orderList);


      // This data is returned data after post just use .whatever field
    this.ordersService.saveOrderForm(orderObject)
    .subscribe((data => {console.log(data.body)}));

    console.log(orderObject);

    }  
    else console.log("issue with ID");

    */

  }

  ngOnInit(): void {
    this.getMenuItems();

    this.getMenuItem('1');

  
  }

}
