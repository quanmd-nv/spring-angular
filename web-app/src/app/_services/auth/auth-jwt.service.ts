import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { map } from "rxjs/operators";
import { Observable } from 'rxjs';

import { AuthRequestModel } from "../../_model";
import { env } from '../../app.constants';
import { SaStorageService } from '../common/local-storage.service';

type JwtToken = {
  id_token: string;
};

@Injectable({
  providedIn: "root"
})
export class AuthServerProvider {
  constructor(
    private http: HttpClient,
    private storageService: SaStorageService
  ) {}

  login(credentials: AuthRequestModel): Observable<void> {
    let url = env.SERVER_API_URL + "/api/auth/authenticate";
    return this.http
      .post<JwtToken>(url, credentials)
      .pipe(
        map(response =>
          this.authenticateSucess(response, credentials.rememberMe)
        )
      );
  }

  getToken(): string {
    return this.storageService.getLocalStorage(env.AUT_TOKEN_STORAGE_KEY) || this.storageService.getSessionStorage(env.AUT_TOKEN_STORAGE_KEY) || '';
  }

  logout(): Observable<void> {
    return new Observable(observer => {
      this.storageService.clearLocalStorage(env.AUT_TOKEN_STORAGE_KEY);
      this.storageService.clearSessionStorage(env.AUT_TOKEN_STORAGE_KEY);
      observer.complete();
    })
  }

  authenticateSucess(response: JwtToken, rememberMe: boolean) {
    const jwt = response.id_token;
    if (rememberMe) {
      this.storageService.storeLocalStorage(env.AUT_TOKEN_STORAGE_KEY, jwt);
    } else {
      this.storageService.storeSessionStorage(env.AUT_TOKEN_STORAGE_KEY, jwt);
    }
  }
}