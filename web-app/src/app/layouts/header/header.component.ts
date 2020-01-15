
import { BsModalService } from 'ngx-bootstrap/modal';
import { AccountService } from './../../_services/account/account.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AuthenticationService } from 'src/app/_services/auth/authentication.service';
import { Router } from '@angular/router';

import { LoginComponent } from './../../shared/login/login.component';
@Component({
  selector: 'sa-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Input()
  username: string;

  @Input()
  imageUrl: string = 'content/image/account.jpg';

  @Output()
  onClickToggled = new EventEmitter<boolean>();

  toggled = true;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    public accountService: AccountService,
    private bsModalService: BsModalService
  ) {}

  ngOnInit() {
  }

  requestLogin() {
    this.bsModalService.show(LoginComponent);
  }

  logout() {
    this.authService.lougout();
    this.router.navigate(['/']);
  }

  register() {
    this.router.navigate(['/account/register']);
  }

  accountPosts() {
    this.router.navigate(['/account/posts']);
  }

  changeToggled() {
    this.toggled = !this.toggled;
    this.onClickToggled.emit(this.toggled);
  }
}
