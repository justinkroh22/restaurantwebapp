import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Services/auth.service';
import {MenuItemClass} from '../../Models/menuitemclass';
import {MenuItemsService} from '../../Services/menuitems.service'

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menuItems: MenuItemClass[] = [];

  public manager: boolean = false;
  public employee: boolean = false;
  private redirectUrl: string = '/Menu';

  constructor(private menuItemsService : MenuItemsService, private authService: AuthService, public router: Router) { }

  ngOnInit(): void {
    this.getMenuItems();
    this.checkUserType();
  }

  getMenuItems(): void{
      this.menuItemsService.getMenuItems()
        .subscribe(menuItems => this.menuItems = menuItems);
  }

  removeMenuItem(id: any): void{
    let menu_id: string =id.toString();
    this.menuItemsService.removeMenuItem(menu_id)
      .subscribe(resp => console.log(resp));
  }

  checkUserType(): void{
    if(this.authService.isLoggedIn){
      if(this.authService.user_type=='EMPLOYEE'){
        this.employee = true;
      }
      
      if(this.authService.user_type=='MANAGER'){
        this.employee = true;
        this.manager = true;
      }
    }
  }

}
