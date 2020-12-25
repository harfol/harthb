import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '@app/shared/shared.module';
import { LedIndicatorComponent } from '@home/components/widget/lib/rpc/led-indicator.component';
import { RoundSwitchComponent } from '@home/components/widget/lib/rpc/round-switch.component';
import { SwitchComponent } from '@home/components/widget/lib/rpc/switch.component';
import { KnobComponent } from '@home/components/widget/lib/rpc/knob.component';

@NgModule({
  declarations:
    [
      LedIndicatorComponent,
      RoundSwitchComponent,
      SwitchComponent,
      KnobComponent
    ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    LedIndicatorComponent,
    RoundSwitchComponent,
    SwitchComponent,
    KnobComponent
  ]
})
export class RpcWidgetsModule { }
