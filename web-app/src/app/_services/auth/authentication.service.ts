import { flatMap } from "rxjs/operators";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { AccountService } from "../account/account.service";
import { AuthRequestModel } from "../../_model";
import { AuthServerProvider } from "./auth-jwt.service";

import { Account } from "../../_model";

@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  constructor(
    private authServerProvider: AuthServerProvider,
    private accountService: AccountService
  ) {}

  login(authRequest: AuthRequestModel): Observable<Account | null> {
    return this.authServerProvider
      .login(authRequest)
      .pipe(flatMap(() => this.accountService.identity(true)));
  }

  lougout() {
    this.authServerProvider
      .logout()
      .subscribe(null, null, () => this.accountService.authenticate(null));
  }
}
