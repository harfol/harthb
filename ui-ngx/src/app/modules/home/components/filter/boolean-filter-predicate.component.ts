import { Component, forwardRef, Input, OnInit } from '@angular/core';
import { ControlValueAccessor, FormBuilder, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import {
  BooleanFilterPredicate,
  BooleanOperation,
  booleanOperationTranslationMap, EntityKeyValueType,
  FilterPredicateType
} from '@shared/models/query/query.models';

@Component({
  selector: 'tb-boolean-filter-predicate',
  templateUrl: './boolean-filter-predicate.component.html',
  styleUrls: ['./filter-predicate.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => BooleanFilterPredicateComponent),
      multi: true
    }
  ]
})
export class BooleanFilterPredicateComponent implements ControlValueAccessor, OnInit {

  @Input() disabled: boolean;

  @Input() allowUserDynamicSource = true;

  valueTypeEnum = EntityKeyValueType;

  booleanFilterPredicateFormGroup: FormGroup;

  booleanOperations = Object.keys(BooleanOperation);
  booleanOperationEnum = BooleanOperation;
  booleanOperationTranslations = booleanOperationTranslationMap;

  private propagateChange = null;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.booleanFilterPredicateFormGroup = this.fb.group({
      operation: [BooleanOperation.EQUAL, [Validators.required]],
      value: [null, [Validators.required]]
    });
    this.booleanFilterPredicateFormGroup.valueChanges.subscribe(() => {
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
      this.booleanFilterPredicateFormGroup.disable({emitEvent: false});
    } else {
      this.booleanFilterPredicateFormGroup.enable({emitEvent: false});
    }
  }

  writeValue(predicate: BooleanFilterPredicate): void {
    this.booleanFilterPredicateFormGroup.get('operation').patchValue(predicate.operation, {emitEvent: false});
    this.booleanFilterPredicateFormGroup.get('value').patchValue(predicate.value, {emitEvent: false});
  }

  private updateModel() {
    let predicate: BooleanFilterPredicate = null;
    if (this.booleanFilterPredicateFormGroup.valid) {
      predicate = this.booleanFilterPredicateFormGroup.getRawValue();
      predicate.type = FilterPredicateType.BOOLEAN;
    }
    this.propagateChange(predicate);
  }

}
