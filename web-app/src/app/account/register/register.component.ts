import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';

import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from '../../shared/constants/error.constants';
import { AccountService } from '../../_services/account/account.service';
import { RegisterModel } from 'src/app/_model';
import { LoginComponent } from 'src/app/shared/login/login.component';

@Component({
  selector: 'sa-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  doNotMatch = false;
  error = false;
  errorEmailExists = false;
  errorUserExists = false;
  success = false;

  registerForm = this.fb.group({
    login: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(50), Validators.pattern('^[_.@A-Za-z0-9-]*$')]],
    email: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(254), Validators.email]],
    password: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]],
    confirmPassword: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]]
  });

  constructor(
    private accountService: AccountService,
    private fb: FormBuilder,
    private bsModelService: BsModalService
  ) {}

  register(): void {
    this.doNotMatch = false;
    this.error = false;
    this.errorEmailExists = false;
    this.errorUserExists = false;

    const password = this.registerForm.get(['password'])!.value;
    if (password !== this.registerForm.get(['confirmPassword'])!.value) {
      this.doNotMatch = true;
    } else {
      let registerModel = new RegisterModel();
      registerModel.username = this.registerForm.get(['login'])!.value;
      registerModel.email = this.registerForm.get(['email'])!.value;
      registerModel.password = password;
      
      this.accountService.register(registerModel).subscribe(
        () => (this.success = true),
        response => this.processError(response)
      );
    }
  }

  openLogin(): void {
    this.bsModelService.show(LoginComponent);
  }

  private processError(response: HttpErrorResponse): void {
    if (response.status === 400 && response.error.type === LOGIN_ALREADY_USED_TYPE) {
      this.errorUserExists = true;
    } else if (response.status === 400 && response.error.type === EMAIL_ALREADY_USED_TYPE) {
      this.errorEmailExists = true;
    } else {
      this.error = true;
    }
  }
}
