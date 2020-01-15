import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { PersonalInfoComponent } from "./my-profile/personal-info/personal-info.component";
import { ProfileComponent } from "./profile.component";

const routes: Routes = [
  {
    path: "",
    component: ProfileComponent
  },
  {
    path: "personal",
    component: PersonalInfoComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule {}
