import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { ResetPasswordRoutingModule } from './reset-password-routing.module';
import { ResetPasswordInitComponent } from './init/reset-password-init.component';
import { ResetPasswordFinishComponent } from './finish/reset-password-finish.component';



@NgModule({
  declarations: [ResetPasswordInitComponent, ResetPasswordFinishComponent],
  imports: [
    CommonModule,
    SharedModule,
    ResetPasswordRoutingModule
  ], 
  exports: [ResetPasswordInitComponent, ResetPasswordFinishComponent]
})
export class ResetPasswordModule { }
