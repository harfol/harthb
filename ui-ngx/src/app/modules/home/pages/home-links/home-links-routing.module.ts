import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeLinksComponent } from './home-links.component';
import { Authority } from '@shared/models/authority.enum';

const routes: Routes = [
  {
    path: 'home',
    component: HomeLinksComponent,
    data: {
      auth: [Authority.SYS_ADMIN, Authority.TENANT_ADMIN, Authority.CUSTOMER_USER],
      title: 'home.home',
      breadcrumb: {
        label: 'home.home',
        icon: 'home'
      }
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeLinksRoutingModule { }
