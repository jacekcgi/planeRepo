import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { DashboardComponent }   from './dashboard/dashboard.component';
import { HomeComponent }  from './home/home.component';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
      AppComponent,
      DashboardComponent,
      HomeComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
