import { Component, forwardRef, Input, OnInit } from '@angular/core';
import {
  ControlValueAccessor,
  FormBuilder,
  FormGroup,
  NG_VALUE_ACCESSOR,
  ValidatorFn,
  Validators
} from '@angular/forms';
import {
  DynamicValueSourceType,
  dynamicValueSourceTypeTranslationMap,
  EntityKeyValueType,
  FilterPredicateValue
} from '@shared/models/query/query.models';

@Component({
  selector: 'tb-filter-predicate-value',
  templateUrl: './filter-predicate-value.component.html',
  styleUrls: ['./filter-predicate.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FilterPredicateValueComponent),
      multi: true
    }
  ]
})
export class FilterPredicateValueComponent implements ControlValueAccessor, OnInit {

  @Input() disabled: boolean;

  @Input()
  set allowUserDynamicSource(allow: boolean) {
    this.dynamicValueSourceTypes = [DynamicValueSourceType.CURRENT_TENANT,
      DynamicValueSourceType.CURRENT_CUSTOMER];
    this.allow = allow;
    if (allow) {
      this.dynamicValueSourceTypes.push(DynamicValueSourceType.CURRENT_USER);
    } else {
      this.dynamicValueSourceTypes = [DynamicValueSourceType.CURRENT_DEVICE];
    }
  }

  @Input()
  valueType: EntityKeyValueType;

  valueTypeEnum = EntityKeyValueType;

  dynamicValueSourceTypes: DynamicValueSourceType[] = [DynamicValueSourceType.CURRENT_TENANT,
    DynamicValueSourceType.CURRENT_CUSTOMER, DynamicValueSourceType.CURRENT_USER];

  dynamicValueSourceTypeTranslations = dynamicValueSourceTypeTranslationMap;

  filterPredicateValueFormGroup: FormGroup;

  dynamicMode = false;

  allow = true;

  private propagateChange = null;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    let defaultValue: string | number | boolean;
    let defaultValueValidators: ValidatorFn[];
    switch (this.valueType) {
      case EntityKeyValueType.STRING:
        defaultValue = '';
        defaultValueValidators = [];
        break;
      case EntityKeyValueType.NUMERIC:
        defaultValue = 0;
        defaultValueValidators = [Validators.required];
        break;
      case EntityKeyValueType.BOOLEAN:
        defaultValue = false;
        defaultValueValidators = [];
        break;
      case EntityKeyValueType.DATE_TIME:
        defaultValue = Date.now();
        defaultValueValidators = [Validators.required];
        break;
    }
    this.filterPredicateValueFormGroup = this.fb.group({
      defaultValue: [defaultValue, defaultValueValidators],
      dynamicValue: this.fb.group(
        {
          sourceType: [null],
          sourceAttribute: [null]
        }
      )
    });
    this.filterPredicateValueFormGroup.get('dynamicValue').get('sourceType').valueChanges.subscribe(
      (sourceType) => {
        if (!sourceType) {
          this.filterPredicateValueFormGroup.get('dynamicValue').get('sourceAttribute').patchValue(null, {emitEvent: false});
        }
      }
    );
    this.filterPredicateValueFormGroup.valueChanges.subscribe(() => {
      this.updateModel();
    });
  }

  registerOnChange(fn: any): void {
    this.propagateChange = fn;
  }

  registerOnTouched(fn: any): void {
  }

  setDisabledState?(isDisabled: boolean): void {
    this.disabled = isDisabled;
    if (this.disabled) {
      this.filterPredicateValueFormGroup.disable({emitEvent: false});
    } else {
      this.filterPredicateValueFormGroup.enable({emitEvent: false});
    }
  }

  writeValue(predicateValue: FilterPredicateValue<string | number | boolean>): void {
    this.filterPredicateValueFormGroup.get('defaultValue').patchValue(predicateValue.defaultValue, {emitEvent: false});
    this.filterPredicateValueFormGroup.get('dynamicValue').get('sourceType').patchValue(predicateValue.dynamicValue ?
      predicateValue.dynamicValue.sourceType : null, {emitEvent: false});
    this.filterPredicateValueFormGroup.get('dynamicValue').get('sourceAttribute').patchValue(predicateValue.dynamicValue ?
      predicateValue.dynamicValue.sourceAttribute : null, {emitEvent: false});
  }

  private updateModel() {
    let predicateValue: FilterPredicateValue<string | number | boolean> = null;
    if (this.filterPredicateValueFormGroup.valid) {
      predicateValue = this.filterPredicateValueFormGroup.getRawValue();
      if (predicateValue.dynamicValue) {
        if (!predicateValue.dynamicValue.sourceType || !predicateValue.dynamicValue.sourceAttribute) {
          predicateValue.dynamicValue = null;
        }
      }
    }
    this.propagateChange(predicateValue);
  }

}
