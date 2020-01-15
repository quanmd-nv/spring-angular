import { BsModalService } from 'ngx-bootstrap/modal';
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { flatMap } from "rxjs/operators";

import {
  AccountService
} from "../../_services/account/account.service";

import { LoginComponent } from 'src/app/shared/login/login.component';

@Component({
  selector: "sa-activate",
  templateUrl: "./activate.component.html",
  styleUrls: ["./activate.component.scss"]
})
export class ActivateComponent implements OnInit {
  success = false;
  error = false;

  constructor(
    private accountService: AccountService,
    private route: ActivatedRoute,
    private bsModalService: BsModalService
  ) {}

  ngOnInit(): void {
    this.route.queryParams
      .pipe(flatMap(params => this.accountService.aciveUser(params.key)))
      .subscribe(
        () => (this.success = true),
        () => (this.error = true)
      );
  }

  login(): void {
    this.bsModalService.show(LoginComponent);
  }
}
