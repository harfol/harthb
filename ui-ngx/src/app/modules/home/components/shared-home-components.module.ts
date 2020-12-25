import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '@app/shared/shared.module';
import { AlarmDetailsDialogComponent } from '@home/components/alarm/alarm-details-dialog.component';

@NgModule({
  declarations:
    [
      AlarmDetailsDialogComponent
    ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    AlarmDetailsDialogComponent
  ]
})
export class SharedHomeComponentsModule { }
