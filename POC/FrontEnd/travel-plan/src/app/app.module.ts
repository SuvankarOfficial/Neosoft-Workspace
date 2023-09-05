import { TravelPlanRoutesModule } from './routing/travel-plan-routes/travel-plan-routes.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    TravelPlanRoutesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
