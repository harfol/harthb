import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EntitiesTableComponent } from '../../components/entity/entities-table.component';
import { Authority } from '@shared/models/authority.enum';
import { AssetsTableConfigResolver } from './assets-table-config.resolver';

const routes: Routes = [
  {
    path: 'assets',
    component: EntitiesTableComponent,
    data: {
      auth: [Authority.TENANT_ADMIN, Authority.CUSTOMER_USER],
      title: 'asset.assets',
      assetsType: 'tenant',
      breadcrumb: {
        label: 'asset.assets',
        icon: 'domain'
      }
    },
    resolve: {
      entitiesTableConfig: AssetsTableConfigResolver
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [
    AssetsTableConfigResolver
  ]
})
export class AssetRoutingModule { }
