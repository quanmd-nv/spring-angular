import { RouterModule } from '@angular/router';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { PasswordStrengthBar } from './password-strength-bar';
import { SidebarComponent } from '../layouts/sidebar/sidebar.component';
import { HeaderComponent } from '../layouts/header/header.component';
import { FooterComponent } from '../layouts/footer/footer.component';



@NgModule({
  declarations: [LoginComponent, PasswordStrengthBar, SidebarComponent, HeaderComponent, FooterComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    PaginationModule,
    RouterModule
  ],
  entryComponents: [LoginComponent],
  exports: [LoginComponent, SidebarComponent, HeaderComponent, FooterComponent, PasswordStrengthBar, PaginationModule, ReactiveFormsModule, FormsModule]
})
export class SharedModule { }
