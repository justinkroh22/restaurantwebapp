import {MenuItemClass} from './menuitemclass';

export class OrderClass {

    // order_id: int
    orderType: string;
    status: string;
    customer_id: number;
    deliveryAddress: string;
    billingAddress: string;
    itemsOrdered: MenuItemClass[];



    constructor(orderType: string, status:string, customer_id: number,
         deliveryAddress: string, billingAddress: string, itemsOrdered: MenuItemClass[]){

        this.orderType = orderType;
        this.status = status;
        this.customer_id = customer_id;
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = billingAddress;
        this.itemsOrdered = itemsOrdered;

    }




  }