import { HttpClient } from '@angular/common/http';
import { Injectable, ɵCompiler_compileModuleSync__POST_R3__ } from '@angular/core';
import { Observable } from 'rxjs';
import { Menu } from '../Models/menu';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private menuUrl = 'http://localhost:8081/api/menuitems';

  constructor(private http: HttpClient) { }

  getMenu(): Observable<Menu[]>{
    let menuItemsUnsorted = this.http.get<Menu[]>(this.menuUrl);
    
    return menuItemsUnsorted;
  }

  removeMenuItem(id: string): void{
    console.log("Menu ID to remove: " + id);
  }

  // sortList(menuItemsUnsorted:Observable<Menu[]>) : Observable<Menu[]>{
  //   let menuItemsSorted = menuItemsUnsorted;
  //   menuItemsUnsorted.forEach(item => { item.sort();
  //     console.log(item);
  //     for(var i = 0; i < item.length; i++){
  //       let j = 1
  //       console.log("Menu Item menu_id" + item[i].menu_id);

  //       while(j < item.length)
  //       {
  //         let first = item[i];
  //         let second = item[j];
  //         if(first.menu_id>second.menu_id){
  //           console.log(first.menu_id + " is less than " + second.menu_id)
  //           let tempItem = first;
  //           first = second;
  //           second = tempItem;
  //           console.log(first.menu_id);
  //           console.log(second.menu_id);
  //         }
  //         else
  //         {
  //           console.log(second.menu_id + " is more than " + first.menu_id)
  //           // let tempItem = second;
  //           // second = first;
  //           // first = tempItem;
  //           console.log(first.menu_id);
  //           console.log(second.menu_id);
  //         }
  //         j++
  //       }
  //       j=1;
  //     }
  //     menuItemsSorted = menuItemsUnsorted
  //   });

  //   return menuItemsSorted;
  // }
}
