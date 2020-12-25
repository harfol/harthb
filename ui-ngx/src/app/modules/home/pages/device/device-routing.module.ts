import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EntitiesTableComponent } from '../../components/entity/entities-table.component';
import { Authority } from '@shared/models/authority.enum';
import { DevicesTableConfigResolver } from '@modules/home/pages/device/devices-table-config.resolver';

const routes: Routes = [
  {
    path: 'devices',
    component: EntitiesTableComponent,
    data: {
      auth: [Authority.TENANT_ADMIN, Authority.CUSTOMER_USER],
      title: 'device.devices',
      devicesType: 'tenant',
      breadcrumb: {
        label: 'device.devices',
        icon: 'devices_other'
      }
    },
    resolve: {
      entitiesTableConfig: DevicesTableConfigResolver
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [
    DevicesTableConfigResolver
  ]
})
export class DeviceRoutingModule { }
