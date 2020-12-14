import { Component, OnInit } from '@angular/core';
import {Menu} from '../../Models/menu';
import {MenuService} from '../../Services/menu.service'

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menuItems: Menu[] = [];

  public manager = false;

  constructor(private menuService : MenuService) { }

  ngOnInit(): void {
    this.getMenuItems();
  }

  getMenuItems(): void{
      this.menuService.getMenu()
        .subscribe(menuItems => this.menuItems = menuItems);
  }

  // removeMenuItem(id: string): void{
  //   this.menuService.removeMenuItem();
  // }

}
