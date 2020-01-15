import { Injectable } from '@angular/core';
import { SaStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class StateStorageService {

  private previousUrl = 'previousUrl';

  constructor(private $storageService: SaStorageService) { }

  storeUrl(url : string): void {
    this.$storageService.storeSessionStorage(this.previousUrl, url);
  }

  getUrl() : string| null| undefined {
    return this.$storageService.getSessionStorage(this.previousUrl);
  }

  clearUrl(): void {
    this.$storageService.clearSessionStorage(this.previousUrl);
  }
}
