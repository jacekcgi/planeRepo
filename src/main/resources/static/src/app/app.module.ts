import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { TranslateModule, TranslateLoader } from 'ng2-translate';
import { CustomLoader } from './translate/translate.loader';

import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { MapComponent } from './home/map.component';
import { FlightRoutesComponent } from "./flight_routes/flightroutes.component";

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ActionService, NotificationService, TranslationService, PlaneService, LanguageService, FlightRoutesService, UserService } from './services';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SimpleNotificationsModule, NotificationsService } from 'angular2-notifications';

import { CommonComponentsModule } from 'common/common.components.module'

import { PlanesComponent } from './planes/planes.component';
import { PlaneDetailsComponent } from './planes/plane.details.component';
import { ActionsColumnComponent } from 'app/planes/actions.column.component';
import { UsersComponent } from './user/users.component';
import { UserDetailsComponent } from './user/user.details.component';
import { UserActionsColumnComponent } from './user/user.actions.column.component';
import { FlightRouteDetailsComponent } from "app/flight_routes/flightroutes.details.component";

import { Ng2AutoCompleteModule } from 'ng2-auto-complete';


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
    SimpleNotificationsModule.forRoot(),
    CommonComponentsModule,
    Ng2AutoCompleteModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    HomeComponent,
    PlaneDetailsComponent,
    FlightRoutesComponent,
    FlightRouteDetailsComponent,
    // root components
    PlanesComponent,
    UsersComponent,
    UserDetailsComponent,
    MapComponent,
    ActionsColumnComponent,
    UserActionsColumnComponent
  ],
  entryComponents: [ActionsColumnComponent, UserActionsColumnComponent],
  providers: [ActionService, PlaneService, NotificationsService, NotificationService, TranslationService, LanguageService, FlightRoutesService,
    UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
