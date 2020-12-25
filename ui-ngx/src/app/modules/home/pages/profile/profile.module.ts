import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile.component';
import { SharedModule } from '@shared/shared.module';
import { ProfileRoutingModule } from './profile-routing.module';
import { ChangePasswordDialogComponent } from '@modules/home/pages/profile/change-password-dialog.component';

@NgModule({
  declarations: [
    ProfileComponent,
    ChangePasswordDialogComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ProfileRoutingModule
  ]
})
export class ProfileModule { }
