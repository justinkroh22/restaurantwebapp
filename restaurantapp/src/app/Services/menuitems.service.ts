import { Injectable } from '@angular/core';
import { MenuItemClass } from '../Models/menuitemclass';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class MenuItemsService {



  private menuItemsUrl = 'http://localhost:8081/restaurantappserver/api/menuitems';

  constructor(private http: HttpClient) { }

  getMenuItems(): Observable<MenuItemClass[]> {

    console.log(this.http.get<MenuItemClass[]>(this.menuItemsUrl));
    return this.http.get<MenuItemClass[]>(this.menuItemsUrl);
  }

  getMenuItem(id: string): Observable<MenuItemClass> {

    console.log(this.http.get<MenuItemClass>(this.menuItemsUrl + '/m/' + id));
    return this.http.get<MenuItemClass>(this.menuItemsUrl + '/m/' + id);

  }

  saveMenuItem(menuitem: MenuItemClass): Observable<any> {
    return this.http.post<any>(this.menuItemsUrl, menuitem, {
      observe: 'response'
    });
  }

//This is how it is done to grab respone data from posts
   httpOptions: any = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    observe: 'response'
  };

  saveMenuItemForm(any: any): Observable<any> {
    return this.http.post<any>(this.menuItemsUrl, any, this.httpOptions);
  }

}
