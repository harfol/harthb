import { NgModule } from '@angular/core';

import { AdminModule } from './admin/admin.module';
import { HomeLinksModule } from './home-links/home-links.module';
import { ProfileModule } from './profile/profile.module';
import { TenantModule } from '@modules/home/pages/tenant/tenant.module';
import { CustomerModule } from '@modules/home/pages/customer/customer.module';
import { AuditLogModule } from '@modules/home/pages/audit-log/audit-log.module';
import { UserModule } from '@modules/home/pages/user/user.module';
import { DeviceModule } from '@modules/home/pages/device/device.module';
import { AssetModule } from '@modules/home/pages/asset/asset.module';
import { EntityViewModule } from '@modules/home/pages/entity-view/entity-view.module';
import { RuleChainModule } from '@modules/home/pages/rulechain/rulechain.module';
import { WidgetLibraryModule } from '@modules/home/pages/widget/widget-library.module';
import { DashboardModule } from '@modules/home/pages/dashboard/dashboard.module';
import { TenantProfileModule } from './tenant-profile/tenant-profile.module';
import { MODULES_MAP } from '@shared/public-api';
import { modulesMap } from '../../common/modules-map';
import { DeviceProfileModule } from './device-profile/device-profile.module';
import { ApiUsageModule } from '@home/pages/api-usage/api-usage.module';

@NgModule({
  exports: [
    AdminModule,
    HomeLinksModule,
    ProfileModule,
    TenantProfileModule,
    TenantModule,
    DeviceProfileModule,
    DeviceModule,
    AssetModule,
    EntityViewModule,
    CustomerModule,
    RuleChainModule,
    WidgetLibraryModule,
    DashboardModule,
    AuditLogModule,
    ApiUsageModule,
    UserModule
  ],
  providers: [
    {
      provide: MODULES_MAP,
      useValue: modulesMap
    }
  ]
})
export class HomePagesModule { }
