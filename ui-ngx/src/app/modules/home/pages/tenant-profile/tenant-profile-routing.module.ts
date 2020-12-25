import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EntitiesTableComponent } from '../../components/entity/entities-table.component';
import { Authority } from '@shared/models/authority.enum';
import { TenantProfilesTableConfigResolver } from './tenant-profiles-table-config.resolver';

const routes: Routes = [
  {
    path: 'tenantProfiles',
    data: {
      breadcrumb: {
        label: 'tenant-profile.tenant-profiles',
        icon: 'mdi:alpha-t-box'
      }
    },
    children: [
      {
        path: '',
        component: EntitiesTableComponent,
        data: {
          auth: [Authority.SYS_ADMIN],
          title: 'tenant-profile.tenant-profiles'
        },
        resolve: {
          entitiesTableConfig: TenantProfilesTableConfigResolver
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [
    TenantProfilesTableConfigResolver
  ]
})
export class TenantProfileRoutingModule { }
