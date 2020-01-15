import { Component, OnInit, Input } from '@angular/core';
import { SidebarItem } from 'src/app/_model/sidebar-item.model';
import { LayoutsService } from 'src/app/_services/layouts/layouts.service';

@Component({
  selector: 'sa-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  @Input()
  toggled = true;

  sidebarItems: Array<SidebarItem>;

  showSidebar = false;

  constructor(public layoutsService : LayoutsService) { }

  ngOnInit() {
    this.layoutsService.sidebar$().subscribe(items => {
      this.sidebarItems = items;
      if (this.sidebarItems !== null && this.sidebarItems.length > 0) {
        this.showSidebar= true;
      }
    });
  }

}
