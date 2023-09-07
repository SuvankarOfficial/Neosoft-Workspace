import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from 'src/app/pages/login/login.component';
import { RegisterLoginPageComponent } from 'src/app/pages/register-login-page/register-login-page.component';

const routes: Routes = [
  { path : "", component: RegisterLoginPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class TravelPlanRoutesModule { }
