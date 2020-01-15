import { env } from './../../app.constants';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmailTemplate } from 'src/app/_model';

@Injectable({
  providedIn: 'root'
})
export class EmailTemplateService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<EmailTemplate>> {
    return this.http.get<Array<EmailTemplate>>(env.SERVER_API_URL + '/api/admin/email/get-all');
  }

  addTemplate(template: EmailTemplate): Observable<void> {
    return this.http.post<void>(env.SERVER_API_URL + '/api/admin/email/add-email-template', template);
  }
}
