import { CompleteProfilePageComponent } from './../../pages/complete-profile-page/complete-profile-page.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from 'src/app/pages/login/login.component';
import { RegisterLoginPageComponent } from 'src/app/pages/register-login-page/register-login-page.component';

const routes: Routes = [
  { path : "", component: RegisterLoginPageComponent},
  { path : "complete-profile", component: CompleteProfilePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class TravelPlanRoutesModule { }
