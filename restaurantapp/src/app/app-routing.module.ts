import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import {CustomersComponent} from './Components/customers/customers.component';
import {EmployeeComponent} from './Components/employees/employee.component';
import { MenuComponent } from './Components/menu/menu.component';
import {ReservationsComponent} from './Components/reservations/reservations.component';

const routes: Routes = [
  { path: 'Customers', component: CustomersComponent },
  { path: 'Employee', component: EmployeeComponent },
  { path: 'Reservation', component: ReservationsComponent },
  { path: 'Menu', component: MenuComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
