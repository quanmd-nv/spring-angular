import { Router } from "@angular/router";
import { Observable, of, ReplaySubject, BehaviorSubject } from "rxjs";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, tap, shareReplay } from "rxjs/operators";

import { StateStorageService } from "../common/state-storage.service";
import { RegisterModel, PasswordAndKey } from "../../_model";
import { env } from "../../app.constants";

import { Account } from "../../_model";

@Injectable({
  providedIn: "root"
})
export class AccountService {
  private userPresenceState = new BehaviorSubject<boolean>(null);
  private accountCached$: Observable<Account | null>;
  private authenticationStats = new ReplaySubject<Account | null>(1);

  constructor(
    private httpClient: HttpClient,
    private stateStorageService: StateStorageService,
    private router: Router
  ) {}

  resetPasswordInit(email: String): Observable<void> {
    return this.httpClient.post<any>(
      env.SERVER_API_URL + "/api/account/reset-password/init",
      email
    );
  }

  resetPasswordFinish(model : PasswordAndKey) : Observable<any> {
    return this.httpClient.post<any>(env.SERVER_API_URL + '/api/account/reset-password/finish', model);
  }

  register(user: RegisterModel): Observable<void> {
    return this.httpClient.post<any>(
      env.SERVER_API_URL + "/api/register",
      user
    );
  }
  fetch(): Observable<Account> {
    return this.httpClient.get<Account>(env.SERVER_API_URL + "/api/account");
  }

  aciveUser(key: string): Observable<{}> {
    return this.httpClient.get(env.SERVER_API_URL + "/api/activate", {
      params: new HttpParams().set("key", key)
    });
  }

  isAuthenticated$(): Observable<boolean> {
    return this.userPresenceState.asObservable();
  }

  authenticate(user: Account) {
    this.userPresenceState.next(user !== null);
    this.authenticationStats.next(user);
  }

  authenticationStats$(): Observable<Account | null> {
    return this.authenticationStats.asObservable();
  }

  identity(force?: boolean) {
    if (!this.accountCached$ || force || !this.userPresenceState.value) {
      this.accountCached$ = this.fetch().pipe(
        catchError(() => {
          return of(null);
        }),
        tap((account: Account) => {
          this.authenticate(account);

          if (account) {
            this.navigateToStoredUrl();
          }
        }, shareReplay())
      );
    }
    return this.accountCached$;
  }

  private navigateToStoredUrl(): void {
    // previousState can be set in the authExpiredInterceptor and in the userRouteAccessService
    // if login is successful, go to stored previousState and clear previousState
    const previousUrl = this.stateStorageService.getUrl();
    if (previousUrl) {
      this.stateStorageService.clearUrl();
      this.router.navigateByUrl(previousUrl);
    }
  }
}
