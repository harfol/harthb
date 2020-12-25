import { Component, forwardRef, Input, OnInit } from '@angular/core';
import { ControlValueAccessor, FormBuilder, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { AppState } from '@app/core/core.state';
import { coerceBooleanProperty } from '@angular/cdk/coercion';
import {
  DeviceTransportConfiguration,
  DeviceTransportType, Lwm2mDeviceTransportConfiguration
} from '@shared/models/device.models';

@Component({
  selector: 'tb-lwm2m-device-transport-configuration',
  templateUrl: './lwm2m-device-transport-configuration.component.html',
  styleUrls: [],
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => Lwm2mDeviceTransportConfigurationComponent),
    multi: true
  }]
})
export class Lwm2mDeviceTransportConfigurationComponent implements ControlValueAccessor, OnInit {

  lwm2mDeviceTransportConfigurationFormGroup: FormGroup;

  private requiredValue: boolean;
  get required(): boolean {
    return this.requiredValue;
  }
  @Input()
  set required(value: boolean) {
    this.requiredValue = coerceBooleanProperty(value);
  }

  @Input()
  disabled: boolean;

  private propagateChange = (v: any) => { };

  constructor(private store: Store<AppState>,
              private fb: FormBuilder) {
  }

  registerOnChange(fn: any): void {
    this.propagateChange = fn;
  }

  registerOnTouched(fn: any): void {
  }

  ngOnInit() {
    this.lwm2mDeviceTransportConfigurationFormGroup = this.fb.group({
      configuration: [null, Validators.required]
    });
    this.lwm2mDeviceTransportConfigurationFormGroup.valueChanges.subscribe(() => {
      this.updateModel();
    });
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
    if (this.disabled) {
      this.lwm2mDeviceTransportConfigurationFormGroup.disable({emitEvent: false});
    } else {
      this.lwm2mDeviceTransportConfigurationFormGroup.enable({emitEvent: false});
    }
  }

  writeValue(value: Lwm2mDeviceTransportConfiguration | null): void {
    this.lwm2mDeviceTransportConfigurationFormGroup.patchValue({configuration: value}, {emitEvent: false});
  }

  private updateModel() {
    let configuration: DeviceTransportConfiguration = null;
    if (this.lwm2mDeviceTransportConfigurationFormGroup.valid) {
      configuration = this.lwm2mDeviceTransportConfigurationFormGroup.getRawValue().configuration;
      // configuration.type = DeviceTransportType.LWM2M;
    }
    this.propagateChange(configuration);
  }
}
