import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import { PersonalInfoComponent } from './my-profile/personal-info/personal-info.component';
import { ProfileComponent } from './profile.component';


@NgModule({
  declarations: [PersonalInfoComponent, ProfileComponent],
  imports: [
    CommonModule,
    ProfileRoutingModule
  ]
})
export class ProfileModule { }
