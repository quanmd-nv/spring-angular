import { BsModalService } from 'ngx-bootstrap/modal';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/_services/account/account.service';
import { PasswordAndKey } from 'src/app/_model';
import { LoginComponent } from 'src/app/shared/login/login.component';

@Component({
  selector: 'sa-reset-password-finish',
  templateUrl: './reset-password-finish.component.html',
  styleUrls: ['./reset-password-finish.component.css']
})
export class ResetPasswordFinishComponent implements OnInit {

  initialized = false;
  doNotMatch = false;
  error = false;
  success = false;
  key = '';

  passwordForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private accountService: AccountService,
    private bsModalService: BsModalService
  ) {
    this.passwordForm = this.fb.group({
      newPassword: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]]
    });
   }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['key']) {
        this.key = params['key'];
      }
      this.initialized = true;
    });
  }

  finishReset(): void {
    this.doNotMatch = false;
    this.error = false;

    const newPassword = this.passwordForm.get(['newPassword'])!.value;
    const confirmPassword = this.passwordForm.get(['confirmPassword'])!.value;

    if (newPassword !== confirmPassword) {
      this.doNotMatch = true;
    } else {
      let model = new PasswordAndKey();
      model.key = this.key;
      model.newPassword = newPassword;
      this.accountService.resetPasswordFinish(model).subscribe(
        () => (this.success = true),
        () => (this.error = true)
      );
    }
  }

  login(): void {
    this.bsModalService.show(LoginComponent);
  }

}
