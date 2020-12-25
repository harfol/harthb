import { MatDialogRef } from '@angular/material/dialog';
import { Inject, InjectionToken } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from '@core/core.state';
import { Router } from '@angular/router';
import { PageComponent } from '@shared/components/page.component';
import { CustomDialogContainerComponent } from './custom-dialog-container.component';
import { FormBuilder, Validators } from '@angular/forms';

export const CUSTOM_DIALOG_DATA = new InjectionToken<any>('ConfigDialogData');

export interface CustomDialogData {
  controller: (instance: CustomDialogComponent) => void;
  [key: string]: any;
}

export class CustomDialogComponent extends PageComponent {

  [key: string]: any;

  constructor(protected store: Store<AppState>,
              protected router: Router,
              public dialogRef: MatDialogRef<CustomDialogContainerComponent>,
              public fb: FormBuilder,
              @Inject(CUSTOM_DIALOG_DATA) public data: CustomDialogData) {
    super(store);
    // @ts-ignore
    this.validators = Validators;
    this.data.controller(this);
  }
}
