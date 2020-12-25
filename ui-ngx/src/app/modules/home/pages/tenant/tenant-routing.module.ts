import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EntitiesTableComponent } from '../../components/entity/entities-table.component';
import { Authority } from '@shared/models/authority.enum';
import { TenantsTableConfigResolver } from '@modules/home/pages/tenant/tenants-table-config.resolver';
import { UsersTableConfigResolver } from '../user/users-table-config.resolver';

const routes: Routes = [
  {
    path: 'tenants',
    data: {
      breadcrumb: {
        label: 'tenant.tenants',
        icon: 'supervisor_account'
      }
    },
    children: [
      {
        path: '',
        component: EntitiesTableComponent,
        data: {
          auth: [Authority.SYS_ADMIN],
          title: 'tenant.tenants'
        },
        resolve: {
          entitiesTableConfig: TenantsTableConfigResolver
        }
      },
      {
        path: ':tenantId/users',
        component: EntitiesTableComponent,
        data: {
          auth: [Authority.SYS_ADMIN],
          title: 'user.tenant-admins',
          breadcrumb: {
            label: 'user.tenant-admins',
            icon: 'account_circle'
          }
        },
        resolve: {
          entitiesTableConfig: UsersTableConfigResolver
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [
    TenantsTableConfigResolver
  ]
})
export class TenantRoutingModule { }
