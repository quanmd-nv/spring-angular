import { Router } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { Component, OnInit } from "@angular/core";
import { BsModalRef } from "ngx-bootstrap/modal";
import { AuthRequestModel } from "src/app/_model";
import { AuthenticationService } from 'src/app/_services/auth/authentication.service';

@Component({
  selector: "sa-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"]
})
export class LoginComponent implements OnInit {
  authenticationError = false;

  loginForm: FormGroup;

  constructor(
    public bsModalRef: BsModalRef,
    private fb: FormBuilder,
    private authenticationService: AuthenticationService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

  login() {
    let username = this.loginForm.get(["username"]).value;
    let password = this.loginForm.get(["password"]).value;
    let remember = this.loginForm.get(["remember"]).value;
    let credentials = new AuthRequestModel(username, password, remember);

    this.authenticationService.login(credentials).subscribe(
      () => {
        this.authenticationError = false;
          this.bsModalRef.hide();
          if (
            this.router.url === '/account/register' ||
            this.router.url.startsWith('/account/activate') ||
            this.router.url.startsWith('/account/reset-password/')
          ) {
            this.router.navigate(['']);
          }
      },
      () => {
        this.authenticationError = true;
      }
    );
  }

  register() {
    this.router.navigate(['/account/register']);
    this.bsModalRef.hide();
  }

  forgotPasword() {
    this.router.navigate(['/account/reset-password/init']);
    this.bsModalRef.hide();
  }
}
