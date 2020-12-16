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

  public manager = true;

  constructor(private menuItemsService : MenuItemsService) { }

  ngOnInit(): void {
    this.getMenuItems();
    // this.removeMenuItem(14);
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

}
