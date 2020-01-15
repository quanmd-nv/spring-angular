import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { ResetPasswordInitComponent } from "./init/reset-password-init.component";
import { ResetPasswordFinishComponent } from './finish/reset-password-finish.component';

const routes: Routes = [
  {
    path: "init",
    component: ResetPasswordInitComponent
  },
  {
    path: "finish",
    component: ResetPasswordFinishComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResetPasswordRoutingModule {}
