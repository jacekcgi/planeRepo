import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { TranslateModule, TranslateLoader } from 'ng2-translate';
import { CustomLoader } from './translate/translate.loader';

import { DashboardComponent }   from './dashboard/dashboard.component';
import { HomeComponent }  from './home/home.component';
import { MapComponent }  from './home/map.component';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ActionService, PlaneService } from './services';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { PlanesComponent } from './planes/planes.component';
import { ErrorMessagesComponent } from 'common/validations';
import { InputComponent } from 'common/input';
import { SimpleNotificationsModule, NotificationsService } from 'angular2-notifications';

import { Table } from './planes/table.component';


@NgModule({
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule,
    TranslateModule.forRoot({
      provide: TranslateLoader,
      useClass: CustomLoader
    }),
    SimpleNotificationsModule.forRoot()
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    HomeComponent,
    // root components
    PlanesComponent,
    InputComponent,
    ErrorMessagesComponent,
    MapComponent,
    Table
  ],
  providers: [ActionService, PlaneService, NotificationsService],
  bootstrap: [AppComponent]
})
export class AppModule { }