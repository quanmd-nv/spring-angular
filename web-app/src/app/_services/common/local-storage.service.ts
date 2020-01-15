import { Injectable, Inject } from "@angular/core";
import {
  LOCAL_STORAGE,
  SESSION_STORAGE,
  StorageService
} from "ngx-webstorage-service";

@Injectable({
  providedIn: "root"
})
export class SaStorageService {
  constructor(
    @Inject(LOCAL_STORAGE)
    private localStorageService: StorageService,
    @Inject(SESSION_STORAGE)
    private sessionStorageService: StorageService
  ) {}

  storeLocalStorage(key: string, value: any): void {
    this.localStorageService.set(key, value);
  }

  storeSessionStorage(key: string, value: any): void {
    this.sessionStorageService.set(key, value);
  }

  getLocalStorage(key: string): any {
    return this.localStorageService.get(key);
  }

  getSessionStorage(key: string): any {
    return this.sessionStorageService.get(key);
  }

  clearLocalStorage(key: string): any {
    this.localStorageService.remove(key);
  }

  clearSessionStorage(key: string): any {
    this.sessionStorageService.remove(key);
  }
}
