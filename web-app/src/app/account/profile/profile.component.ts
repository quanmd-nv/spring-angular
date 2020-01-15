import { Component, OnInit } from '@angular/core';
import { LayoutsService } from 'src/app/_services/layouts/layouts.service';
import { SidebarSubItem } from 'src/app/_model/sidebar-sub-item.model';
import { SidebarItem } from 'src/app/_model/sidebar-item.model';

@Component({
  selector: 'sa-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  
  constructor(private layoutService: LayoutsService) { }

  ngOnInit() {
    let personalSubItem = new SidebarSubItem();
    personalSubItem.name = 'Personal Info';
    personalSubItem.icon = 'fas fa-user';
    let subItems = new Array<SidebarSubItem>();
    subItems.push(personalSubItem);

    let personalItem = new SidebarItem();
    personalItem.title = 'My Profile';
    personalItem.icon = 'fas fa-id-card';
    personalItem.items = subItems;

    let profile = new Array<SidebarItem>();
    profile.push(personalItem);
    this.layoutService.registerSidebar(profile);
  }
}
