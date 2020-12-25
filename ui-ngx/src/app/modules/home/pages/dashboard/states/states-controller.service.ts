import { ComponentFactory, ComponentFactoryResolver, Injectable, Type } from '@angular/core';
import { deepClone } from '@core/utils';
import { IStateControllerComponent } from '@home/pages/dashboard/states/state-controller.models';

export interface StateControllerData {
  factory: ComponentFactory<IStateControllerComponent>;
  state?: any;
}

@Injectable()
export class StatesControllerService {

  statesControllers: {[stateControllerId: string]: StateControllerData} = {};

  constructor(private componentFactoryResolver: ComponentFactoryResolver) {
  }

  public registerStatesController(stateControllerId: string, stateControllerComponent: Type<IStateControllerComponent>): void {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(stateControllerComponent);
    this.statesControllers[stateControllerId] = {
      factory: componentFactory
    };
  }

  public getStateControllers(): {[stateControllerId: string]: StateControllerData} {
    return this.statesControllers;
  }

  public getStateController(stateControllerId: string): StateControllerData {
    return this.statesControllers[stateControllerId];
  }

  public preserveStateControllerState(stateControllerId: string, state: any) {
    this.statesControllers[stateControllerId].state = deepClone(state);
  }

  public withdrawStateControllerState(stateControllerId: string): any {
    const state = this.statesControllers[stateControllerId].state;
    this.statesControllers[stateControllerId].state = null;
    return state;
  }

  public cleanupPreservedStates() {
    for (const stateControllerId of Object.keys(this.statesControllers)) {
      this.statesControllers[stateControllerId].state = null;
    }
  }
}
