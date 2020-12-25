import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UsersTableConfigResolver } from '@modules/home/pages/user/users-table-config.resolver';

@NgModule({
  imports: [],
  exports: [RouterModule],
  providers: [
    UsersTableConfigResolver
  ]
})
export class UserRoutingModule { }
