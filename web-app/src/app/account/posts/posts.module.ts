import { SharedModule } from './../../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostsRoutingModule } from './posts-routing.module';
import { AddPostComponent } from './add-post/add-post.component';
import { PostsComponent } from './posts.component';


@NgModule({
  declarations: [AddPostComponent, PostsComponent],
  imports: [
    CommonModule,
    PostsRoutingModule,
    SharedModule
  ],
  exports: [PostsComponent]
})
export class PostsModule { }
