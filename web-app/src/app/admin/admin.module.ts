import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { EmailTemplateComponent } from './email-template/email-template.component';


@NgModule({
  declarations: [EmailTemplateComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [EmailTemplateComponent]
})
export class AdminModule { }
