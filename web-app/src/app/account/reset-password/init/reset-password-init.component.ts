import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Component, OnInit } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { AccountService } from "src/app/_services/account/account.service";
import { EMAIL_NOT_FOUND_TYPE } from 'src/app/shared/constants/error.constants';


@Component({
  selector: "sa-reset-password-init",
  templateUrl: "./reset-password-init.component.html",
  styleUrls: ["./reset-password-init.component.css"]
})
export class ResetPasswordInitComponent implements OnInit {
  success = false;
  error = false;
  errorEmailNotExists = false;

  emailSubmitForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService
  ) {}

  ngOnInit() {
    this.emailSubmitForm = this.fb.group({
      email: [
        "",
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(254),
          Validators.email
        ]
      ]
    });
  }

  sendEmail() {
    this.error = false;
    this.errorEmailNotExists = false;
    let email: string = this.emailSubmitForm.get(["email"])!.value;
    this.accountService.resetPasswordInit(email).subscribe(
      () => (this.success = true),
      (response: HttpErrorResponse) => {
        if (response.status === 400 && response.error.type === EMAIL_NOT_FOUND_TYPE) {
          this.errorEmailNotExists = true;
        } else {
          this.error = true;
        }
      }
    );
  }
}
