import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Store } from '@ngrx/store';
import { AppState } from '@core/core.state';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActionNotificationShow } from '@core/notification/notification.actions';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from '@core/auth/auth.service';
import { DialogComponent } from '@shared/components/dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'tb-change-password-dialog',
  templateUrl: './change-password-dialog.component.html',
  styleUrls: ['./change-password-dialog.component.scss']
})
export class ChangePasswordDialogComponent extends DialogComponent<ChangePasswordDialogComponent> implements OnInit {

  changePassword: FormGroup;

  constructor(protected store: Store<AppState>,
              protected router: Router,
              private translate: TranslateService,
              private authService: AuthService,
              public dialogRef: MatDialogRef<ChangePasswordDialogComponent>,
              public fb: FormBuilder) {
    super(store, router, dialogRef);
  }

  ngOnInit(): void {
    this.buildChangePasswordForm();
  }

  buildChangePasswordForm() {
    this.changePassword = this.fb.group({
      currentPassword: [''],
      newPassword: [''],
      newPassword2: ['']
    });
  }

  onChangePassword(): void {
    if (this.changePassword.get('newPassword').value !== this.changePassword.get('newPassword2').value) {
      this.store.dispatch(new ActionNotificationShow({ message: this.translate.instant('login.passwords-mismatch-error'),
        type: 'error' }));
    } else {
      this.authService.changePassword(
        this.changePassword.get('currentPassword').value,
        this.changePassword.get('newPassword').value).subscribe(() => {
          this.dialogRef.close(true);
      });
    }
  }
}
