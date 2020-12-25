import { Component, OnInit } from '@angular/core';
import { AuthService } from '@core/auth/auth.service';
import { Store } from '@ngrx/store';
import { AppState } from '@core/core.state';
import { PageComponent } from '@shared/components/page.component';
import { FormBuilder } from '@angular/forms';
import { ActionNotificationShow } from '@core/notification/notification.actions';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'tb-reset-password-request',
  templateUrl: './reset-password-request.component.html',
  styleUrls: ['./reset-password-request.component.scss']
})
export class ResetPasswordRequestComponent extends PageComponent implements OnInit {

  requestPasswordRequest = this.fb.group({
    email: ['']
  });

  constructor(protected store: Store<AppState>,
              private authService: AuthService,
              private translate: TranslateService,
              public fb: FormBuilder) {
    super(store);
  }

  ngOnInit() {
  }

  sendResetPasswordLink() {
    this.authService.sendResetPasswordLink(this.requestPasswordRequest.get('email').value).subscribe(
      () => {
        this.store.dispatch(new ActionNotificationShow({ message: this.translate.instant('login.password-link-sent-message'),
          type: 'success' }));
      }
    );
  }

}
