import { LayoutsService } from 'src/app/_services/layouts/layouts.service';
import { Component, OnInit } from '@angular/core';
import { SidebarSubItem } from 'src/app/_model/sidebar-sub-item.model';

@Component({
  selector: 'sa-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {

  constructor(private layoutService: LayoutsService) { }

  ngOnInit() {
    let personalSidebarItem = new SidebarSubItem();
    personalSidebarItem.name = 'Persinal info';
  }

}
