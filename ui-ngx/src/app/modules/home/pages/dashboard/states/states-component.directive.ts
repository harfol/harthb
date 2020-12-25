import {
  ComponentRef,
  Directive,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  SimpleChanges,
  ViewContainerRef
} from '@angular/core';
import { DashboardState } from '@shared/models/dashboard.models';
import { IDashboardController } from '@home/pages/dashboard/dashboard-page.models';
import { StatesControllerService } from '@home/pages/dashboard/states/states-controller.service';
import { IStateControllerComponent } from '@home/pages/dashboard/states/state-controller.models';

@Directive({
  // tslint:disable-next-line:directive-selector
  selector: 'tb-states-component'
})
export class StatesComponentDirective implements OnInit, OnDestroy, OnChanges {

  @Input()
  statesControllerId: string;

  @Input()
  dashboardCtrl: IDashboardController;

  @Input()
  dashboardId: string;

  @Input()
  states: {[id: string]: DashboardState };

  @Input()
  state: string;

  @Input()
  isMobile: boolean;

  stateControllerComponentRef: ComponentRef<IStateControllerComponent>;
  stateControllerComponent: IStateControllerComponent;

  constructor(private viewContainerRef: ViewContainerRef,
              private statesControllerService: StatesControllerService) {
  }

  ngOnInit(): void {
    this.init();
  }

  ngOnDestroy(): void {
    this.destroy();
  }

  ngOnChanges(changes: SimpleChanges): void {
    let reInitController = false;
    for (const propName of Object.keys(changes)) {
      const change = changes[propName];
      if (!change.firstChange && change.currentValue !== change.previousValue) {
        if (propName === 'statesControllerId') {
          this.reInit();
        } else if (propName === 'states') {
          this.stateControllerComponent.states = this.states;
        } else if (propName === 'dashboardId') {
          this.stateControllerComponent.dashboardId = this.dashboardId;
          reInitController = true;
        } else if (propName === 'isMobile') {
          this.stateControllerComponent.isMobile = this.isMobile;
        } else if (propName === 'state') {
          this.stateControllerComponent.state = this.state;
        }
      }
    }
    if (reInitController) {
      this.stateControllerComponent.reInit();
    }
  }

  private reInit() {
    this.destroy();
    this.init();
  }

  private init() {
    this.viewContainerRef.clear();
    let stateControllerData = this.statesControllerService.getStateController(this.statesControllerId);
    if (!stateControllerData) {
      stateControllerData = this.statesControllerService.getStateController('default');
    }
    const preservedState = this.statesControllerService.withdrawStateControllerState(this.statesControllerId);
    const stateControllerFactory = stateControllerData.factory;
    this.stateControllerComponentRef = this.viewContainerRef.createComponent(stateControllerFactory);
    this.stateControllerComponent = this.stateControllerComponentRef.instance;
    this.dashboardCtrl.dashboardCtx.stateController = this.stateControllerComponent;
    this.stateControllerComponent.preservedState = preservedState;
    this.stateControllerComponent.dashboardCtrl = this.dashboardCtrl;
    this.stateControllerComponent.state = this.state;
    this.stateControllerComponent.isMobile = this.isMobile;
    this.stateControllerComponent.states = this.states;
    this.stateControllerComponent.dashboardId = this.dashboardId;
  }

  private destroy() {
    if (this.stateControllerComponentRef) {
      this.stateControllerComponentRef.destroy();
    }
  }
}
