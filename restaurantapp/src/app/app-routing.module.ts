import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import {CustomersComponent} from './Components/customers/customers.component';
import {EmployeeComponent} from './Components/employees/employee.component';
import {ReservationsComponent} from './Components/reservations/reservations.component';
import {MenuItemsComponent} from './Components/menuitems/menuitems.component';
import {OrdersComponent} from './Components/orders/orders.component';

const routes: Routes = [
  { path: 'Customers', component: CustomersComponent },
  { path: 'Employee', component: EmployeeComponent },
  { path: 'Reservation', component: ReservationsComponent },
  { path: 'Menu/newItem', component: MenuItemsComponent },
  { path: 'Orders/newOrder', component: OrdersComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
