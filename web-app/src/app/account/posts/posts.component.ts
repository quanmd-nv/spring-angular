import { Component, OnInit } from '@angular/core';
import { AccountPost } from 'src/app/_model/account-post.model';

@Component({
  selector: 'sa-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  listPosts: Array<AccountPost> = [];
  constructor() { }

  ngOnInit() {
    this.listPosts.length
  }

}
