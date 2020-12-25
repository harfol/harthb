import { Component, Inject } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from '@core/core.state';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from '@shared/models/customer.model';
import { ActionNotificationShow } from '@app/core/notification/notification.actions';
import { TranslateService } from '@ngx-translate/core';
import { ContactBasedComponent } from '../../components/entity/contact-based.component';
import { EntityTableConfig } from '@home/models/entity/entities-table-config.models';

@Component({
  selector: 'tb-customer',
  templateUrl: './customer.component.html'
})
export class CustomerComponent extends ContactBasedComponent<Customer> {

  isPublic = false;

  constructor(protected store: Store<AppState>,
              protected translate: TranslateService,
              @Inject('entity') protected entityValue: Customer,
              @Inject('entitiesTableConfig') protected entitiesTableConfigValue: EntityTableConfig<Customer>,
              protected fb: FormBuilder) {
    super(store, fb, entityValue, entitiesTableConfigValue);
  }

  hideDelete() {
    if (this.entitiesTableConfig) {
      return !this.entitiesTableConfig.deleteEnabled(this.entity);
    } else {
      return false;
    }
  }

  buildEntityForm(entity: Customer): FormGroup {
    return this.fb.group(
      {
        title: [entity ? entity.title : '', [Validators.required]],
        additionalInfo: this.fb.group(
          {
            description: [entity && entity.additionalInfo ? entity.additionalInfo.description : '']
          }
        )
      }
    );
  }

  updateEntityForm(entity: Customer) {
    this.isPublic = entity.additionalInfo && entity.additionalInfo.isPublic;
    this.entityForm.patchValue({title: entity.title});
    this.entityForm.patchValue({additionalInfo: {description: entity.additionalInfo ? entity.additionalInfo.description : ''}});
  }

  onCustomerIdCopied(event) {
    this.store.dispatch(new ActionNotificationShow(
      {
        message: this.translate.instant('customer.idCopiedMessage'),
        type: 'success',
        duration: 750,
        verticalPosition: 'bottom',
        horizontalPosition: 'right'
      }));
  }

}
