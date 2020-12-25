import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from '@core/core.state';
import { PageComponent } from '@shared/components/page.component';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SecuritySettings } from '@shared/models/settings.models';
import { AdminService } from '@core/http/admin.service';
import { HasConfirmForm } from '@core/guards/confirm-on-exit.guard';

@Component({
  selector: 'tb-security-settings',
  templateUrl: './security-settings.component.html',
  styleUrls: ['./security-settings.component.scss', './settings-card.scss']
})
export class SecuritySettingsComponent extends PageComponent implements OnInit, HasConfirmForm {

  securitySettingsFormGroup: FormGroup;
  securitySettings: SecuritySettings;

  constructor(protected store: Store<AppState>,
              private router: Router,
              private adminService: AdminService,
              public fb: FormBuilder) {
    super(store);
  }

  ngOnInit() {
    this.buildSecuritySettingsForm();
    this.adminService.getSecuritySettings().subscribe(
      (securitySettings) => {
        this.securitySettings = securitySettings;
        this.securitySettingsFormGroup.reset(this.securitySettings);
      }
    );
  }

  buildSecuritySettingsForm() {
    this.securitySettingsFormGroup = this.fb.group({
      maxFailedLoginAttempts: [null, [Validators.min(0)]],
      userLockoutNotificationEmail: ['', []],
      passwordPolicy: this.fb.group(
        {
          minimumLength: [null, [Validators.required, Validators.min(5), Validators.max(50)]],
          minimumUppercaseLetters: [null, Validators.min(0)],
          minimumLowercaseLetters: [null, Validators.min(0)],
          minimumDigits: [null, Validators.min(0)],
          minimumSpecialCharacters: [null, Validators.min(0)],
          passwordExpirationPeriodDays: [null, Validators.min(0)],
          passwordReuseFrequencyDays: [null, Validators.min(0)]
        }
      )
    });
  }

  save(): void {
    this.securitySettings = {...this.securitySettings, ...this.securitySettingsFormGroup.value};
    this.adminService.saveSecuritySettings(this.securitySettings).subscribe(
      (securitySettings) => {
        this.securitySettings = securitySettings;
        this.securitySettingsFormGroup.reset(this.securitySettings);
      }
    );
  }

  confirmForm(): FormGroup {
    return this.securitySettingsFormGroup;
  }

}
