import { Component, OnInit } from '@angular/core';
import { UserService } from '@core/http/user.service';
import { User } from '@shared/models/user.model';
import { Authority } from '@shared/models/authority.enum';
import { PageComponent } from '@shared/components/page.component';
import { Store } from '@ngrx/store';
import { AppState } from '@core/core.state';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HasConfirmForm } from '@core/guards/confirm-on-exit.guard';
import { ActionAuthUpdateUserDetails } from '@core/auth/auth.actions';
import { environment as env } from '@env/environment';
import { TranslateService } from '@ngx-translate/core';
import { ActionSettingsChangeLanguage } from '@core/settings/settings.actions';
import { ChangePasswordDialogComponent } from '@modules/home/pages/profile/change-password-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { DialogService } from '@core/services/dialog.service';
import { AuthService } from '@core/auth/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'tb-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent extends PageComponent implements OnInit, HasConfirmForm {

  authorities = Authority;
  profile: FormGroup;
  user: User;
  languageList = env.supportedLangs;

  constructor(protected store: Store<AppState>,
              private route: ActivatedRoute,
              private userService: UserService,
              private authService: AuthService,
              private translate: TranslateService,
              public dialog: MatDialog,
              public dialogService: DialogService,
              public fb: FormBuilder) {
    super(store);
  }

  ngOnInit() {
    this.buildProfileForm();
    this.userLoaded(this.route.snapshot.data.user);
  }

  buildProfileForm() {
    this.profile = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      firstName: [''],
      lastName: [''],
      language: ['']
    });
  }

  save(): void {
    this.user = {...this.user, ...this.profile.value};
    if (!this.user.additionalInfo) {
      this.user.additionalInfo = {};
    }
    this.user.additionalInfo.lang = this.profile.get('language').value;
    this.userService.saveUser(this.user).subscribe(
      (user) => {
        this.userLoaded(user);
        this.store.dispatch(new ActionAuthUpdateUserDetails({ userDetails: {
            additionalInfo: {...user.additionalInfo},
            authority: user.authority,
            createdTime: user.createdTime,
            tenantId: user.tenantId,
            customerId: user.customerId,
            email: user.email,
            firstName: user.firstName,
            id: user.id,
            lastName: user.lastName,
          } }));
        this.store.dispatch(new ActionSettingsChangeLanguage({ userLang: user.additionalInfo.lang }));
      }
    );
  }

  changePassword(): void {
    this.dialog.open(ChangePasswordDialogComponent, {
      disableClose: true,
      panelClass: ['tb-dialog', 'tb-fullscreen-dialog']
    });
  }

  userLoaded(user: User) {
    this.user = user;
    this.profile.reset(user);
    let lang;
    if (user.additionalInfo && user.additionalInfo.lang) {
      lang = user.additionalInfo.lang;
    } else {
      lang = this.translate.currentLang;
    }
    this.profile.get('language').setValue(lang);
  }

  confirmForm(): FormGroup {
    return this.profile;
  }

}
