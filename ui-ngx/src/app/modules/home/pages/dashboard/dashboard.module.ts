import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '@shared/shared.module';
import { HomeDialogsModule } from '../../dialogs/home-dialogs.module';
import { DashboardFormComponent } from '@modules/home/pages/dashboard/dashboard-form.component';
import { ManageDashboardCustomersDialogComponent } from '@modules/home/pages/dashboard/manage-dashboard-customers-dialog.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { MakeDashboardPublicDialogComponent } from '@modules/home/pages/dashboard/make-dashboard-public-dialog.component';
import { HomeComponentsModule } from '@modules/home/components/home-components.module';
import { DashboardTabsComponent } from '@home/pages/dashboard/dashboard-tabs.component';
import { DashboardPageComponent } from '@home/pages/dashboard/dashboard-page.component';
import { DashboardToolbarComponent } from './dashboard-toolbar.component';
import { StatesControllerModule } from '@home/pages/dashboard/states/states-controller.module';
import { DashboardLayoutComponent } from './layout/dashboard-layout.component';
import { EditWidgetComponent } from './edit-widget.component';
import { DashboardWidgetSelectComponent } from './dashboard-widget-select.component';
import { AddWidgetDialogComponent } from './add-widget-dialog.component';
import { ManageDashboardLayoutsDialogComponent } from './layout/manage-dashboard-layouts-dialog.component';
import { DashboardSettingsDialogComponent } from './dashboard-settings-dialog.component';
import { ManageDashboardStatesDialogComponent } from './states/manage-dashboard-states-dialog.component';
import { DashboardStateDialogComponent } from './states/dashboard-state-dialog.component';

@NgModule({
  declarations: [
    DashboardFormComponent,
    DashboardTabsComponent,
    ManageDashboardCustomersDialogComponent,
    MakeDashboardPublicDialogComponent,
    DashboardToolbarComponent,
    DashboardPageComponent,
    DashboardLayoutComponent,
    EditWidgetComponent,
    DashboardWidgetSelectComponent,
    AddWidgetDialogComponent,
    ManageDashboardLayoutsDialogComponent,
    DashboardSettingsDialogComponent,
    ManageDashboardStatesDialogComponent,
    DashboardStateDialogComponent
  ],
  exports: [
    DashboardPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    HomeComponentsModule,
    HomeDialogsModule,
    StatesControllerModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule { }
