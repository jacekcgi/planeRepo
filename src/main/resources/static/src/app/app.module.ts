import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { TranslateModule, TranslateLoader } from 'ng2-translate';
import { CustomLoader } from './translate/translate.loader';

import { DashboardComponent }   from './dashboard/dashboard.component';
import { HomeComponent }  from './home/home.component';
import { MapComponent }  from './home/map.component';

import { SidebarComponent }  from './home/sidebar.component';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ActionService, PlaneService, LanguageService, FlightRoutesService } from './services';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SimpleNotificationsModule, NotificationsService } from 'angular2-notifications';

import { CommonComponentsModule } from 'common/common.components.module'

import { PlanesComponent } from './planes/planes.component';
import { PlaneDetailsComponent} from './planes/plane.details.component';
import { ActionsColumnComponent } from 'app/planes/actions.column.component';
import { SidebarActionsColumnComponent } from 'app/home/sidebar.actions.column.component';

import { NotificationService, TranslationService, AirportService } from 'app/services'; // app notifications
import { FlightRouteDetailsComponent } from "app/flight_routes/flightroutes.details.component";
import { FlightRoutesComponent } from "app/flight_routes/flightroutes.component";

import { Ng2AutoCompleteModule } from 'ng2-auto-complete';
import { DatePipe } from "@angular/common";
import { DatepickerModule, TimepickerModule } from 'ng2-bootstrap';
import { MomentPipe } from "common/datetimepicker";


@NgModule({
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    Ng2AutoCompleteModule,
    DatepickerModule.forRoot(),
    TimepickerModule.forRoot(),
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule,
    TranslateModule.forRoot({
      provide: TranslateLoader,
      useClass: CustomLoader
    }),
    SimpleNotificationsModule.forRoot(),
    CommonComponentsModule
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
    MapComponent,
    SidebarComponent,
    ActionsColumnComponent,
    SidebarActionsColumnComponent
  ],
  entryComponents: [ActionsColumnComponent,SidebarActionsColumnComponent],
  providers: [ActionService, PlaneService, NotificationsService, NotificationService, TranslationService, LanguageService, FlightRoutesService, AirportService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
