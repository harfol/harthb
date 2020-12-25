import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '@shared/shared.module';
import { HomeComponentsModule } from '@modules/home/components/home-components.module';
import { StatesControllerService } from './states-controller.service';
import { EntityStateControllerComponent } from './entity-state-controller.component';
import { StatesComponentDirective } from './states-component.directive';
import { HomeDialogsModule } from '@app/modules/home/dialogs/home-dialogs.module';
import { DefaultStateControllerComponent } from '@home/pages/dashboard/states/default-state-controller.component';

@NgModule({
  declarations: [
    StatesComponentDirective,
    DefaultStateControllerComponent,
    EntityStateControllerComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    HomeComponentsModule,
    HomeDialogsModule
  ],
  exports: [
    StatesComponentDirective
  ],
  providers: [
    StatesControllerService
  ]
})
export class StatesControllerModule {

  constructor(private statesControllerService: StatesControllerService) {
    this.statesControllerService.registerStatesController('default', DefaultStateControllerComponent);
    this.statesControllerService.registerStatesController('entity', EntityStateControllerComponent);
  }
}
