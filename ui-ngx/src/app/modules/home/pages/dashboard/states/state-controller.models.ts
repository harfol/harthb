import { IStateController, StateObject } from '@core/api/widget-api.models';
import { IDashboardController } from '@home/pages/dashboard/dashboard-page.models';
import { DashboardState } from '@shared/models/dashboard.models';

export declare type StateControllerState = StateObject[];

export interface IStateControllerComponent extends IStateController {
  state: string;
  isMobile: boolean;
  dashboardCtrl: IDashboardController;
  states: {[id: string]: DashboardState };
  dashboardId: string;
  preservedState: any;
  reInit(): void;
}
