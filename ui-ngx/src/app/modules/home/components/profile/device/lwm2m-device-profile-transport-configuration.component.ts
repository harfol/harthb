import { Component, forwardRef, Input, OnInit } from '@angular/core';
import { ControlValueAccessor, FormBuilder, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { AppState } from '@app/core/core.state';
import { coerceBooleanProperty } from '@angular/cdk/coercion';
import {
  DeviceProfileTransportConfiguration,
  DeviceTransportType, Lwm2mDeviceProfileTransportConfiguration
} from '@shared/models/device.models';

@Component({
  selector: 'tb-lwm2m-device-profile-transport-configuration',
  templateUrl: './lwm2m-device-profile-transport-configuration.component.html',
  styleUrls: [],
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => Lwm2mDeviceProfileTransportConfigurationComponent),
    multi: true
  }]
})
export class Lwm2mDeviceProfileTransportConfigurationComponent implements ControlValueAccessor, OnInit {

  lwm2mDeviceProfileTransportConfigurationFormGroup: FormGroup;

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
    this.lwm2mDeviceProfileTransportConfigurationFormGroup = this.fb.group({
      configuration: [null, Validators.required]
    });
    this.lwm2mDeviceProfileTransportConfigurationFormGroup.valueChanges.subscribe(() => {
      this.updateModel();
    });
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
    if (this.disabled) {
      this.lwm2mDeviceProfileTransportConfigurationFormGroup.disable({emitEvent: false});
    } else {
      this.lwm2mDeviceProfileTransportConfigurationFormGroup.enable({emitEvent: false});
    }
  }

  writeValue(value: Lwm2mDeviceProfileTransportConfiguration | null): void {
    this.lwm2mDeviceProfileTransportConfigurationFormGroup.patchValue({configuration: value}, {emitEvent: false});
  }

  private updateModel() {
    let configuration: DeviceProfileTransportConfiguration = null;
    if (this.lwm2mDeviceProfileTransportConfigurationFormGroup.valid) {
      configuration = this.lwm2mDeviceProfileTransportConfigurationFormGroup.getRawValue().configuration;
      // configuration.type = DeviceTransportType.LWM2M;
    }
    this.propagateChange(configuration);
  }
}
