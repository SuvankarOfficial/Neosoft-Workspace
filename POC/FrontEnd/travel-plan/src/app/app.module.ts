import { TravelPlanRoutesModule } from './routing/travel-plan-routes/travel-plan-routes.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterLoginPageComponent } from './pages/register-login-page/register-login-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { HttpClientModule, HttpParams } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterLoginPageComponent,
    RegisterPageComponent
  ],
  imports: [
    BrowserModule,
    TravelPlanRoutesModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
