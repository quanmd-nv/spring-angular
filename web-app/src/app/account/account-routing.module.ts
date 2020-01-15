import { ActivateComponent } from "./activate/activate.component";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RegisterComponent } from "./register/register.component";

const routes: Routes = [
  {
    path: "activate",
    component: ActivateComponent,
    data: {
      authorities: [],
      pageTitle: "Activation"
    }
  },
  {
    path: "register",
    component: RegisterComponent,
    data: {
      authorities: [],
      pageTitle: "Registration"
    }
  },
  {
    path: "reset-password",
    loadChildren: () => import('./reset-password/reset-password.module').then(m => m.ResetPasswordModule)
  },
  {
    path: "profile",
    loadChildren: () => import('./profile/profile.module').then(m => m.ProfileModule)
  },
  {
    path: "posts",
    loadChildren: () => import("./posts/posts.module").then(m => m.PostsModule),
    data: {
      authorities: [],
      pageTitle: "Posts"
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule {}
