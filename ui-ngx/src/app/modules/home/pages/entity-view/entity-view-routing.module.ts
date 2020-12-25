import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EntitiesTableComponent } from '../../components/entity/entities-table.component';
import { Authority } from '@shared/models/authority.enum';
import { EntityViewsTableConfigResolver } from '@modules/home/pages/entity-view/entity-views-table-config.resolver';

const routes: Routes = [
  {
    path: 'entityViews',
    component: EntitiesTableComponent,
    data: {
      auth: [Authority.TENANT_ADMIN, Authority.CUSTOMER_USER],
      title: 'entity-view.entity-views',
      entityViewsType: 'tenant',
      breadcrumb: {
        label: 'entity-view.entity-views',
        icon: 'view_quilt'
      }
    },
    resolve: {
      entitiesTableConfig: EntityViewsTableConfigResolver
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [
    EntityViewsTableConfigResolver
  ]
})
export class EntityViewRoutingModule { }
