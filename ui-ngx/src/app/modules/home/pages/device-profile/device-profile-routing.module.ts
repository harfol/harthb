import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EntitiesTableComponent } from '../../components/entity/entities-table.component';
import { Authority } from '@shared/models/authority.enum';
import { DeviceProfilesTableConfigResolver } from './device-profiles-table-config.resolver';

const routes: Routes = [
  {
    path: 'deviceProfiles',
    data: {
      breadcrumb: {
        label: 'device-profile.device-profiles',
        icon: 'mdi:alpha-d-box'
      }
    },
    children: [
      {
        path: '',
        component: EntitiesTableComponent,
        data: {
          auth: [Authority.TENANT_ADMIN],
          title: 'device-profile.device-profiles'
        },
        resolve: {
          entitiesTableConfig: DeviceProfilesTableConfigResolver
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [
    DeviceProfilesTableConfigResolver
  ]
})
export class DeviceProfileRoutingModule { }
