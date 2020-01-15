import { AccountService } from './_services/account/account.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'sa-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  loggedInUser: string;

  toggled = false;

  public constructor(private accountService: AccountService) {
    
  }

  ngOnInit(): void {
    this.accountService.identity().subscribe();
    this.accountService.authenticationStats$().subscribe((account) => {
      if (account) {
        this.loggedInUser =  account.username;
      }
    });
  }


}
