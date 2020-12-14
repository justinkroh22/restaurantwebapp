import { Component, OnInit } from '@angular/core';
import {MenuItemClass} from '../../Models/menuitemclass';
import {MenuItemsService} from '../../Services/menuitems.service'

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menuItems: MenuItemClass[] = [];

  public manager = false;

  constructor(private menuItemsService : MenuItemsService) { }

  ngOnInit(): void {
    this.getMenuItems();
  }

  getMenuItems(): void{
      this.menuItemsService.getMenuItems()
        .subscribe(menuItems => this.menuItems = menuItems);
  }

  // removeMenuItem(id: string): void{
  //   this.menuService.removeMenuItem();
  // }

}
