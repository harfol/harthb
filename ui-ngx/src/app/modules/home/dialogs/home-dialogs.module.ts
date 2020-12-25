import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '@app/shared/shared.module';
import { AssignToCustomerDialogComponent } from '@modules/home/dialogs/assign-to-customer-dialog.component';
import { AddEntitiesToCustomerDialogComponent } from '@modules/home/dialogs/add-entities-to-customer-dialog.component';
import { HomeDialogsService } from './home-dialogs.service';

@NgModule({
  declarations:
  [
    AssignToCustomerDialogComponent,
    AddEntitiesToCustomerDialogComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    AssignToCustomerDialogComponent,
    AddEntitiesToCustomerDialogComponent
  ],
  providers: [
    HomeDialogsService
  ]
})
export class HomeDialogsModule { }
