import { env } from './../app.constants';
import { Observable } from 'rxjs';
import { Injectable } from "@angular/core";
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest
} from "@angular/common/http";
import { SaStorageService } from '../_services/common/local-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private localStorage: SaStorageService
  ) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (
      !request ||
      !request.url ||
      (request.url.startsWith("http") &&
        !(env.SERVER_API_URL && request.url.startsWith(env.SERVER_API_URL)))
    ) {
      return next.handle(request);
    }

    const token =
      this.localStorage.getLocalStorage(env.AUT_TOKEN_STORAGE_KEY) ||
      this.localStorage.getSessionStorage(env.AUT_TOKEN_STORAGE_KEY);
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: "Bearer " + token
        }
      });
    }
    return next.handle(request);
  }
}
