import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { SidebarItem } from 'src/app/_model/sidebar-item.model';

@Injectable({
  providedIn: 'root'
})
export class LayoutsService {

  sidebarItemsSubject = new BehaviorSubject<Array<SidebarItem>>([]);

  constructor() { }

  registerSidebar( items : Array<SidebarItem>) {
    this.sidebarItemsSubject.next(items);
  }

  sidebar$() : Observable<Array<SidebarItem>> {
    return this.sidebarItemsSubject.asObservable();
  }
}
