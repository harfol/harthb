import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '@app/shared/shared.module';
import { EntitiesTableWidgetComponent } from '@home/components/widget/lib/entities-table-widget.component';
import { DisplayColumnsPanelComponent } from '@home/components/widget/lib/display-columns-panel.component';
import { AlarmsTableWidgetComponent } from '@home/components/widget/lib/alarms-table-widget.component';
import { AlarmFilterPanelComponent } from '@home/components/widget/lib/alarm-filter-panel.component';
import { SharedHomeComponentsModule } from '@home/components/shared-home-components.module';
import { TimeseriesTableWidgetComponent } from '@home/components/widget/lib/timeseries-table-widget.component';
import { EntitiesHierarchyWidgetComponent } from '@home/components/widget/lib/entities-hierarchy-widget.component';
import { CustomDialogService } from '@home/components/widget/dialog/custom-dialog.service';
import { RpcWidgetsModule } from '@home/components/widget/lib/rpc/rpc-widgets.module';
import {
  DateRangeNavigatorPanelComponent,
  DateRangeNavigatorWidgetComponent
} from '@home/components/widget/lib/date-range-navigator/date-range-navigator.component';
import { MultipleInputWidgetComponent } from './lib/multiple-input-widget.component';
import { TripAnimationComponent } from './trip-animation/trip-animation.component';
import { PhotoCameraInputWidgetComponent } from './lib/photo-camera-input.component';
import { GatewayFormComponent } from './lib/gateway/gateway-form.component';
import { ImportExportService } from '@home/components/import-export/import-export.service';

@NgModule({
  declarations:
    [
      DisplayColumnsPanelComponent,
      AlarmFilterPanelComponent,
      EntitiesTableWidgetComponent,
      AlarmsTableWidgetComponent,
      TimeseriesTableWidgetComponent,
      EntitiesHierarchyWidgetComponent,
      DateRangeNavigatorWidgetComponent,
      DateRangeNavigatorPanelComponent,
      MultipleInputWidgetComponent,
      TripAnimationComponent,
      PhotoCameraInputWidgetComponent,
      GatewayFormComponent
    ],
  imports: [
    CommonModule,
    SharedModule,
    RpcWidgetsModule,
    SharedHomeComponentsModule
  ],
  exports: [
    EntitiesTableWidgetComponent,
    AlarmsTableWidgetComponent,
    TimeseriesTableWidgetComponent,
    EntitiesHierarchyWidgetComponent,
    RpcWidgetsModule,
    DateRangeNavigatorWidgetComponent,
    MultipleInputWidgetComponent,
    TripAnimationComponent,
    PhotoCameraInputWidgetComponent,
    GatewayFormComponent
  ],
  providers: [
    CustomDialogService,
    ImportExportService
  ]
})
export class WidgetComponentsModule { }
