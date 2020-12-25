import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeLinksRoutingModule } from './home-links-routing.module';
import { HomeLinksComponent } from './home-links.component';
import { SharedModule } from '@app/shared/shared.module';

@NgModule({
  declarations:
    [
      HomeLinksComponent
    ],
  imports: [
    CommonModule,
    SharedModule,
    HomeLinksRoutingModule
  ]
})
export class HomeLinksModule { }
