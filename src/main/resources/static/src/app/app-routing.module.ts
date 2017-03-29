import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent }   from './dashboard/dashboard.component';
import { HomeComponent }      from './home/home.component';
import { PlanesComponent }      from './planes/planes.component';
import { PlaneDetailsComponent }      from './planes/plane.details.component';
import { FlightRoutesComponent } from "app/flight_routes/flightroutes.component";
import { FlightRouteDetailsComponent } from "app/flight_routes/flightroutes.details.component";


const routes: Routes = [
  { path: '', redirectTo: 'menu/home', pathMatch: 'full' },
  { path: 'menu/dashboard',  component: DashboardComponent },
  { path: 'menu/home', component: HomeComponent },
  { path: 'menu/planes', component: PlanesComponent },
  { path: 'menu/plane', component: PlaneDetailsComponent },
  { path: 'menu/flights', component: FlightRoutesComponent },
  { path: 'menu/flight', component: FlightRouteDetailsComponent}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
