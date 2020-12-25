import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from '@core/guards/auth.guard';
import { ResetPasswordRequestComponent } from '@modules/login/pages/login/reset-password-request.component';
import { ResetPasswordComponent } from '@modules/login/pages/login/reset-password.component';
import { CreatePasswordComponent } from '@modules/login/pages/login/create-password.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'login.login',
      module: 'public'
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'login/resetPasswordRequest',
    component: ResetPasswordRequestComponent,
    data: {
      title: 'login.request-password-reset',
      module: 'public'
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'login/resetPassword',
    component: ResetPasswordComponent,
    data: {
      title: 'login.reset-password',
      module: 'public'
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'login/resetExpiredPassword',
    component: ResetPasswordComponent,
    data: {
      title: 'login.reset-password',
      module: 'public',
      expiredPassword: true
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'login/createPassword',
    component: CreatePasswordComponent,
    data: {
      title: 'login.create-password',
      module: 'public'
    },
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
