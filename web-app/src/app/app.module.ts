
import { AccountModule } from './account/account.module';
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppComponent } from "./app.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

/** config angular i18n **/
import { FormsModule } from "@angular/forms";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { ModalModule } from 'ngx-bootstrap/modal';
import { PaginationModule } from 'ngx-bootstrap/pagination';

import { StorageServiceModule } from "ngx-webstorage-service";
import { AuthInterceptor } from "./_interceptor/auth.interceptor";

import { AppRoutingModule } from "./app-routing.module";
import { SharedModule } from './shared/shared.module';
import { CollapseModule } from 'ngx-bootstrap/collapse';


@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    StorageServiceModule,
    SharedModule,
    ModalModule.forRoot(),
    PaginationModule.forRoot()
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
