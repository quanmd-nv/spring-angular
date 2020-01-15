import { Observable } from 'rxjs';
import { env } from './app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TestRequest } from './_model/test-request.model';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) { }

  addItem(request : TestRequest) : Observable<void> {
    return this.http.post<any>(env.SERVER_API_URL + "/test/add-sidebar", request);
  }
}
