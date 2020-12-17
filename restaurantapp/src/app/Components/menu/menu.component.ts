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

  constructor(private menuItemsService : MenuItemsService, private authService: AuthService, public router: Router) { }

  ngOnInit(): void {
    this.getMenuItems();
  }

  getMenuItems(): void{
      this.menuItemsService.getMenuItems()
        .subscribe(menuItems => this.menuItems = menuItems);
  }

  removeMenuItem(id: any): void{
    let menu_id: string =id.toString();
    this.menuItemsService.removeMenuItem(menu_id)
      .subscribe(resp => console.log(resp));
    
      window.location.reload();
  }

}
